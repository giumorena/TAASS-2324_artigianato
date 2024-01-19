package com.unito.edu.craftstoreservice.service;

import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.Sampler;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMaxDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMinDto;
import com.unito.edu.craftstoreservice.repository.CraftstoreRepository;
import com.unito.edu.craftstoreservice.service.specification.CraftstoreSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
     * @return a list of all craftstores (with only the MinDTO data) that matches with the filters
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
     * This method is used to save in the database a craftstore.
     * @param craftstore the craftstore to be saved
     * @return the saved craftstore
     */
    public Craftstore addCraftstore(Craftstore craftstore){

        return craftstoreRepository.save(craftstore);
    }
}
