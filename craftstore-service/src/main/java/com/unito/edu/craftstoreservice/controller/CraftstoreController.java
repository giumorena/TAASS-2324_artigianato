package com.unito.edu.craftstoreservice.controller;

import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreDto;
import com.unito.edu.craftstoreservice.service.CraftstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CraftstoreController {

    @Autowired
    private CraftstoreService craftstoreService;

    /**
     * This API is used to get all craftstores.
     *
     * @return a list of all craftstores.
     */
    @GetMapping("/getCraftstores")
    public List<Craftstore> getAllCraftstores() {

        return craftstoreService.getAllCraftstores();
    }

    /**
     * This API is used to search craftstores based on filters.
     * @param name the craftstore name (optional)
     * @param category the craftstore category (optional)
     * @param region the craftstore region (optional)
     * @param province the craftstore province (optional)
     * @param city the craftstore city (optional)
     * @return a list of all craftstores (with only the DTO data) that matches with the filters
     */
    @GetMapping("/searchCraftstores")
    public List<CraftstoreDto> searchCraftstoresByFilters(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String category,
                                                          @RequestParam (required = false) String region,
                                                          @RequestParam (required = false) String province,
                                                          @RequestParam (required = false) String city) {

        return craftstoreService.searchCraftstoresByFilters(name,category,region,province,city);
    }

    /**
     * This API is used to get a craftstore given its id.
     * @param id the craftstore id
     * @return the craftstore with that id
     */
    @GetMapping("/getCraftstore/{id}")
    public Craftstore getCraftstoreById(@PathVariable int id) {

        return craftstoreService.getCraftstoreById(id);
    }

    /**
     * This API is used to save in the database a craftstore.
     * @param craftstore the craftstore to be saved
     * @return the saved craftstore
     */
    @PostMapping(value = "/addCraftstore")
    public Craftstore addCraftstore(@RequestBody Craftstore craftstore){

        return craftstoreService.addCraftstore(craftstore);
    }
}
