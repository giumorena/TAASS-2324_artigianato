package com.unito.edu.craftstoreservice.service;

import com.unito.edu.craftstoreservice.model.Comment;
import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.Sampler;
import com.unito.edu.craftstoreservice.model.dto.CommentMaxDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreInfoDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMaxDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMinDto;
import com.unito.edu.craftstoreservice.repository.CraftstoreRepository;
import com.unito.edu.craftstoreservice.service.specification.CraftstoreSpecification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CraftstoreService {

    @Autowired
    private CraftstoreRepository craftstoreRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * This method is used to get all craftstores.
     *
     * @return a list of all craftstores.
     */
    public List<Craftstore> getAllCraftstores() {

        return craftstoreRepository.findAll();
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
    public List<CraftstoreMinDto> searchCraftstoresByFilters(String name, String category, String region, String province, String city) {

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

        return list;
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
     * @return a list of craftstores (with only the MinDTO data) that match the filters and belong to the page
     */
    public List<CraftstoreMinDto> searchCraftstoresByFiltersAndPages(String name, String category, String region, String province, String city, int page, int size) {

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

        List<Craftstore> craftstoreList= pageCraftstores.getContent();

        // Mapping to CraftstoreMinDto list
        List<CraftstoreMinDto> list = new ArrayList<>( craftstoreList.size() );
        for ( Craftstore craftstore : craftstoreList ) {
            list.add( new CraftstoreMinDto(craftstore.getId(), craftstore.getName(),
                    craftstore.getCategory(), craftstore.getDescription(), craftstore.getOwnerList()) );
        }

        return list;
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
    public int craftstoresPagesNumber(String name, String category, String region, String province, String city, int page, int size) {

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

        return pageCraftstores.getTotalPages();
    }


    /**
     * This method is used to get a craftstore given its id.
     * @param id the craftstore id
     * @return the craftstore (with all the MaxDTO data) with that id
     */
    public CraftstoreMaxDto getCraftstoreById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        // Call to sampler-microservice to retrieve the craftstore sampler, given its id
        Sampler sampler = restTemplate.getForObject("http://SAMPLER-SERVICE/sampler/getSampler/" + craftstore.get().getSamplerId(),Sampler.class);

        // Mapping to CraftstoreMaxDto Object
        CraftstoreMaxDto craftstoreMaxDto = new CraftstoreMaxDto(craftstore.get().getId(), craftstore.get().getName(),
                craftstore.get().getCategory(),craftstore.get().getDescription(),sampler,craftstore.get().getOwnerList(),
                craftstore.get().getContactList(),craftstore.get().getAddressList(),craftstore.get().getCommentList());

        return craftstoreMaxDto;
    }

    /**
     * This method is used to get craftstore information given its id.
     * @param id the craftstore id
     * @return the information (with only the InfoDTO data) about craftstore with that id
     */
    public CraftstoreInfoDto getCraftstoreInfoById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        // Mapping to CraftstoreInfoDto Object
        CraftstoreInfoDto craftstoreInfoDto = new CraftstoreInfoDto(craftstore.get().getId(), craftstore.get().getName(),
                craftstore.get().getCategory(),craftstore.get().getDescription(),craftstore.get().getOwnerList(),
                craftstore.get().getContactList(),craftstore.get().getAddressList());

        return craftstoreInfoDto;
    }

    /**
     * This method is used to get a craftstore's sampler, given the craftstore id.
     * @param id the craftstore id
     * @return the craftstore's sampler
     */
    public Sampler getCraftstoreSamplerById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        // Call to sampler-microservice to retrieve the craftstore sampler, given its id
        Sampler sampler = restTemplate.getForObject("http://SAMPLER-SERVICE/sampler/getSampler/" + craftstore.get().getSamplerId(),Sampler.class);

        return sampler;
    }

    /**
     * This method is used to get comments related to a craftstore given its id.
     * @param id the craftstore id
     * @return the comments related to the craftstore with that id
     */
    public List<Comment> getCraftstoreCommentsById(int id) {

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);

        return craftstore.get().getCommentList();
    }

    /**
     * This method is used to save in the database a craftstore.
     * @param craftstore the craftstore to be saved
     * @return the saved craftstore
     */
    public Craftstore addCraftstore(Craftstore craftstore){

        return craftstoreRepository.save(craftstore);
    }

    /**
     * This method is used to add a comment to a craftstore comment list.
     * @param id the craftstore id
     * @param comment the comment to be added
     * @return the updated craftstore
     */
    public Craftstore addComment(int id, Comment comment){

        Optional<Craftstore> craftstore= craftstoreRepository.findById(id);
        craftstore.get().getCommentList().add(comment);

        return craftstoreRepository.save(craftstore.get());
    }
}
