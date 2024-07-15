package com.unito.edu.craftstoreservice.service;

import com.unito.edu.craftstoreservice.model.Comment;
import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.Sampler;
import com.unito.edu.craftstoreservice.model.dto.CommentMaxDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreInfoDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMaxDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMinDto;
import com.unito.edu.craftstoreservice.repository.CommentRepository;
import com.unito.edu.craftstoreservice.repository.CraftstoreRepository;
import com.unito.edu.craftstoreservice.service.specification.CraftstoreSpecification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CraftstoreService {

    @Autowired
    private CraftstoreRepository craftstoreRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * This method is used to get all craftstores.
     *
     * @return a list of all craftstores.
     */
    public ResponseEntity<?> getAllCraftstores() {

        List<Craftstore> craftstores = craftstoreRepository.findAll();

        if(craftstores.size()>0){
            return new ResponseEntity<>(craftstores, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(craftstores, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get all craftstores (only MinDto data) sorted by craftstore name.
     *
     * @return a list with all craftstores (only MinDto data) sorted by craftstore name.
     */
    public ResponseEntity<?> getAllSortedCraftstores() {

        List<Craftstore> craftstoreList= craftstoreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        List<CraftstoreMinDto> list = new ArrayList<>( craftstoreList.size() );
        for ( Craftstore craftstore : craftstoreList ) {
            list.add(new CraftstoreMinDto(craftstore.getId(),craftstore.getName(),craftstore.getCategory(),craftstore.getDescription(),craftstore.getOwnerList()));
        }

        if(list.size()>0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to search craftstores based on filters.
     * @param name the craftstore name (optional)
     * @param category the craftstore category (optional)
     * @param region the craftstore region (optional)
     * @param province the craftstore province (optional)
     * @param city the craftstore city (optional)
     * @return a list of all craftstores (with only the MinDTO data) that match the filters
     */
    public ResponseEntity<?> searchCraftstoresByFilters(String name, String category, String region, String province, String city) {

        Specification<Craftstore> filters = Specification.where(null);

        if(name!=null && !name.isBlank()){
            filters=filters.and(CraftstoreSpecification.hasNameLike(name));
        }

        if(category!=null && !category.isBlank()){
            filters=filters.and(CraftstoreSpecification.belongsToCategory(category));
        }

        if(region!=null && !region.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInRegion(region));
        }

        if(province!=null && !province.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInProvince(province));
        }

        if(city!=null && !city.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInCity(city));
        }

        List<Craftstore> craftstoreList= craftstoreRepository.findAll(filters);

        // Mapping to CraftstoreMinDto list
        List<CraftstoreMinDto> list = new ArrayList<>( craftstoreList.size() );
        for ( Craftstore craftstore : craftstoreList ) {
            list.add( new CraftstoreMinDto(craftstore.getId(), craftstore.getName(),
                    craftstore.getCategory(), craftstore.getDescription(), craftstore.getOwnerList()) );
        }

        if(list.size()>0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to search craftstores based on filters and with pagination.
     * @param name the craftstore name (optional)
     * @param category the craftstore category (optional)
     * @param region the craftstore region (optional)
     * @param province the craftstore province (optional)
     * @param city the craftstore city (optional)
     * @param page the page index (one-based, default 1)
     * @param size the page size (default 3)
     * @param sortField the name of the field in the entity object (not in the database table) used for sorting (default name)
     * @param sortDirection the sort direction (default ASC)
     * @return a list of craftstores (with only the MinDTO data) that match the filters and belong to the page, sorted by the sortField
     */
    public ResponseEntity<?> searchCraftstoresByFiltersPaginationSorting(String name, String category, String region, String province, String city, int page, int size, String sortField, String sortDirection) {

        Specification<Craftstore> filters = Specification.where(null);

        if(name!=null && !name.isBlank()){
            filters=filters.and(CraftstoreSpecification.hasNameLike(name));
        }

        if(category!=null && !category.isBlank()){
            filters=filters.and(CraftstoreSpecification.belongsToCategory(category));
        }

        if(region!=null && !region.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInRegion(region));
        }

        if(province!=null && !province.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInProvince(province));
        }

        if(city!=null && !city.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInCity(city));
        }

        // pageNumber parameter is zero-based
        Pageable pagingSort = PageRequest.of(page-1, size, Sort.Direction.fromString(sortDirection), sortField);

        Page<Craftstore> pageCraftstores = craftstoreRepository.findAll(filters, pagingSort);

        List<Craftstore> craftstoreList= pageCraftstores.getContent();

        // Mapping to CraftstoreMinDto list
        List<CraftstoreMinDto> list = new ArrayList<>( craftstoreList.size() );
        for ( Craftstore craftstore : craftstoreList ) {
            list.add( new CraftstoreMinDto(craftstore.getId(), craftstore.getName(),
                    craftstore.getCategory(), craftstore.getDescription(), craftstore.getOwnerList()) );
        }

        if(list.size()>0){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to count the number of craftstores pages that match the filters.
     * @param name the craftstore name (optional)
     * @param category the craftstore category (optional)
     * @param region the craftstore region (optional)
     * @param province the craftstore province (optional)
     * @param city the craftstore city (optional)
     * @param page the page index (one-based, default 1)
     * @param size the page size (default 3)
     * @return the number of craftstores pages that match the filters.
     */
    public ResponseEntity<?> craftstoresPagesNumber(String name, String category, String region, String province, String city, int page, int size) {

        Specification<Craftstore> filters = Specification.where(null);

        if(name!=null && !name.isBlank()){
            filters=filters.and(CraftstoreSpecification.hasNameLike(name));
        }

        if(category!=null && !category.isBlank()){
            filters=filters.and(CraftstoreSpecification.belongsToCategory(category));
        }

        if(region!=null && !region.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInRegion(region));
        }

        if(province!=null && !province.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInProvince(province));
        }

        if(city!=null && !city.isBlank()){
            filters=filters.and(CraftstoreSpecification.isInCity(city));
        }

        // pageNumber parameter is zero-based
        Pageable paging = PageRequest.of(page-1, size);

        Page<Craftstore> pageCraftstores = craftstoreRepository.findAll(filters, paging);

        return new ResponseEntity<>(pageCraftstores.getTotalPages(),HttpStatus.OK);
    }


    /**
     * This method is used to get a craftstore given its id.
     * @param id the craftstore id
     * @return the craftstore (with all the MaxDTO data) with that id
     */
    public ResponseEntity<?> getCraftstoreById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        if(craftstore.isPresent()) {
            // Call to sampler-microservice to retrieve the craftstore sampler, given its id
            ResponseEntity<Sampler> response = restTemplate.getForEntity("http://SAMPLER-SERVICE/sampler/getSampler/" + craftstore.get().getSamplerId(), Sampler.class);

            if(response.getStatusCode() == HttpStatus.OK) {
                Sampler sampler = response.getBody();

                // Mapping to CraftstoreMaxDto Object
                CraftstoreMaxDto craftstoreMaxDto = new CraftstoreMaxDto(craftstore.get().getId(), craftstore.get().getName(),
                        craftstore.get().getCategory(), craftstore.get().getDescription(), sampler, craftstore.get().getOwnerList(),
                        craftstore.get().getContactList(), craftstore.get().getAddressList(), craftstore.get().getCommentList());

                return new ResponseEntity<>(craftstoreMaxDto, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY); //referred to sampler
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * This method is used to get craftstore information given its id.
     * @param id the craftstore id
     * @return the information (with only the InfoDTO data) about craftstore with that id
     */
    public ResponseEntity<?> getCraftstoreInfoById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        if(craftstore.isPresent()) {

            // Mapping to CraftstoreInfoDto Object
            CraftstoreInfoDto craftstoreInfoDto = new CraftstoreInfoDto(craftstore.get().getId(), craftstore.get().getName(),
                    craftstore.get().getCategory(), craftstore.get().getDescription(), craftstore.get().getOwnerList(),
                    craftstore.get().getContactList(), craftstore.get().getAddressList());

            return new ResponseEntity<>(craftstoreInfoDto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get a craftstore's sampler, given the craftstore id.
     * @param id the craftstore id
     * @return the craftstore's sampler
     */
    public ResponseEntity<?> getCraftstoreSamplerById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        if(craftstore.isPresent()) {

            // Call to sampler-microservice to retrieve the craftstore sampler, given its id
            ResponseEntity<Sampler> response = restTemplate.getForEntity("http://SAMPLER-SERVICE/sampler/getSampler/" + craftstore.get().getSamplerId(), Sampler.class);

            if(response.getStatusCode() == HttpStatus.OK){
                Sampler sampler = response.getBody();
                return new ResponseEntity<>(sampler,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY); //referred to sampler
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get comments related to a craftstore given its id, sorted in descending order by post date.
     * @param id the craftstore id
     * @return the comments related to the craftstore with that id, sorted in descending order by post date
     */

    public ResponseEntity<?> getCraftstoreCommentsById(int id) {

        Optional<Craftstore> craftstore = craftstoreRepository.findById(id);

        if(craftstore.isPresent()) {

            List<Comment> comments = commentRepository.findOrderedCommentsByCraftstoreId(id);

            if (comments.size() > 0) {
                return new ResponseEntity<>(comments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(comments, HttpStatus.NOT_FOUND); //referred to comments
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //referred to craftstore id
    }
    /*
    public List<Comment> getCraftstoreCommentsById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        //copy of the comment list
        List<Comment> list = new ArrayList<>( craftstore.get().getCommentList().size() );
        for ( Comment comment : craftstore.get().getCommentList() ) {
            list.add( new Comment(comment.getId(),comment.getUserId(),comment.getUserName(),comment.getPostDate(),comment.getText()));
        }

        //list sorting
        list.sort(Comparator.comparing(Comment::getPostDate).reversed());

        return list;
    }
    */

    /**
     * This method is used to save in the database a craftstore.
     * @param craftstore the craftstore to be saved
     * @return the saved craftstore
     */
    public ResponseEntity<?> addCraftstore(Craftstore craftstore){

        try{
            return new ResponseEntity<>(craftstoreRepository.save(craftstore), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * This method is used to add a comment to a craftstore comment list.
     * @param id the craftstore id
     * @param comment the comment to be added
     * @return the updated craftstore
     */
    public ResponseEntity<?> addComment(int id, Comment comment){

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        if(craftstore.isPresent()) {
            craftstore.get().getCommentList().add(comment);
            try {
                return new ResponseEntity<>(craftstoreRepository.save(craftstore.get()),HttpStatus.OK);
            }
            catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
