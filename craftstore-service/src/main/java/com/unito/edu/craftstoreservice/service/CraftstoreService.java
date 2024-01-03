package com.unito.edu.craftstoreservice.service;

import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMapper;
import com.unito.edu.craftstoreservice.repository.CraftstoreRepository;
import com.unito.edu.craftstoreservice.service.specification.CraftstoreSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraftstoreService {

    @Autowired
    private CraftstoreRepository craftstoreRepository;

    @Autowired
    private CraftstoreMapper craftstoreMapper;

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
     * @return a list of all craftstores (with only the DTO data) that matches with the filters
     */
    public List<CraftstoreDto> searchCraftstoresByFilters(String name, String category, String region, String province, String city) {

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

        return craftstoreMapper.toDtoList(craftstoreRepository.findAll(filters));
    }

    /**
     * This method is used to get a craftstore given its id.
     * @param id the craftstore id
     * @return the craftstore with that id
     */
    public Craftstore getCraftstoreById(int id) {

        return craftstoreRepository.findById(id).orElse(null);
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
