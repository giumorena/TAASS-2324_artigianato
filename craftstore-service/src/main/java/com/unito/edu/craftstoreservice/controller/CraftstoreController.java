package com.unito.edu.craftstoreservice.controller;

import com.unito.edu.craftstoreservice.model.Comment;
import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.Sampler;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreInfoDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMaxDto;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreMinDto;
import com.unito.edu.craftstoreservice.service.CraftstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/craftstore")
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
     * This API is used to get all craftstores (only MinDto data) sorted by craftstore name.
     *
     * @return a list with all craftstores (only MinDto data) sorted by craftstore name.
     */
    @GetMapping("/getSortedCraftstores")
    public List<CraftstoreMinDto> getAllSortedCraftstore() {

        return craftstoreService.getAllSortedCraftstores();
    }

    /**
     * This API is used to search craftstores based on filters.
     * @param name the craftstore name (optional)
     * @param category the craftstore category (optional)
     * @param region the craftstore region (optional)
     * @param province the craftstore province (optional)
     * @param city the craftstore city (optional)
     * @return a list of all craftstores (with only the MinDTO data) that match the filters
     */
    @GetMapping("/searchCraftstores")
    public List<CraftstoreMinDto> searchCraftstoresByFilters(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false) String category,
                                                             @RequestParam (required = false) String region,
                                                             @RequestParam (required = false) String province,
                                                             @RequestParam (required = false) String city) {

        return craftstoreService.searchCraftstoresByFilters(name,category,region,province,city);
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
    @GetMapping("/searchCraftstoresSortedPage")
    public List<CraftstoreMinDto> searchCraftstoresByFiltersPaginationSorting(@RequestParam (required = false) String name,
                                                                              @RequestParam (required = false) String category,
                                                                              @RequestParam (required = false) String region,
                                                                              @RequestParam (required = false) String province,
                                                                              @RequestParam (required = false) String city,
                                                                              @RequestParam (defaultValue = "1") int page,
                                                                              @RequestParam (defaultValue = "3") int size,
                                                                              @RequestParam (defaultValue = "name") String sortField,
                                                                              @RequestParam (defaultValue = "ASC") String sortDirection) {

        return craftstoreService.searchCraftstoresByFiltersPaginationSorting(name,category,region,province,city,page,size,sortField,sortDirection);
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
     * @param sortField the name of the field in the entity object (not in the database table) used for sorting (default name)
     * @param sortDirection the sort direction (default ASC)
     * @return the number of craftstores pages that match the filters.
     */
    @GetMapping("/countCraftstoresPages")
    public int craftstoresPagesNumber(@RequestParam (required = false) String name,
                                      @RequestParam (required = false) String category,
                                      @RequestParam (required = false) String region,
                                      @RequestParam (required = false) String province,
                                      @RequestParam (required = false) String city,
                                      @RequestParam (defaultValue = "1") int page,
                                      @RequestParam (defaultValue = "3") int size,
                                      @RequestParam (defaultValue = "name") String sortField,
                                      @RequestParam (defaultValue = "ASC") String sortDirection) {

        return craftstoreService.craftstoresPagesNumber(name,category,region,province,city,page,size);
    }

    /**
     * This API is used to get a craftstore given its id.
     * @param id the craftstore id
     * @return the craftstore (with all the MaxDTO data) with that id
     */
    @GetMapping("/getCraftstore/{id}")
    public CraftstoreMaxDto getCraftstoreById(@PathVariable int id) {

        return craftstoreService.getCraftstoreById(id);
    }

    /**
     * This API is used to get craftstore information given its id.
     * @param id the craftstore id
     * @return the information (with only the InfoDTO data) about craftstore with that id
     */
    @GetMapping("/getCraftstoreInfo/{id}")
    public CraftstoreInfoDto getCraftstoreInfoById(@PathVariable int id) {

        return craftstoreService.getCraftstoreInfoById(id);
    }

    /**
     * This API is used to get a craftstore's sampler, given the craftstore id.
     * @param id the craftstore id
     * @return the craftstore's sampler
     */
    @GetMapping("/getCraftstoreSampler/{id}")
    public Sampler getCraftstoreSamplerById(@PathVariable int id) {

        return craftstoreService.getCraftstoreSamplerById(id);
    }

    /**
     * This API is used to get comments related to a craftstore given its id, sorted in descending order by post date.
     * @param id the craftstore id
     * @return the comments related to the craftstore with that id, sorted in descending order by post date
     */
    @GetMapping("/getCraftstoreComments/{id}")
    public List<Comment> getCraftstoreCommentsById(@PathVariable int id) {

        return craftstoreService.getCraftstoreCommentsById(id);
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
