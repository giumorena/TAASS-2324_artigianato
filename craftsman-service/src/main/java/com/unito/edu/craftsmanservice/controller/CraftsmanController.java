package com.unito.edu.craftsmanservice.controller;

import com.unito.edu.craftsmanservice.model.Craftsman;
import com.unito.edu.craftsmanservice.service.CraftsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CraftsmanController {

    @Autowired
    private CraftsmanService craftsmanService;

    /**
     * This API is used to get all craftsmen.
     *
     * @return a list of all craftsmen.
     */
    @GetMapping("/getCraftsmen")
    public List<Craftsman> getAllCraftsmen() {

        return craftsmanService.getAllCraftsmen();
    }

    /**
     * This API is used to get a craftsman given its id.
     * @param id the craftsman id
     * @return the craftsman with that id
     */
    @GetMapping("/getCraftsman/{id}")
    public Craftsman getCraftsmanById(@PathVariable int id) {

        return craftsmanService.getCraftsmanById(id);
    }

    /**
     * This method is used to save in the database a craftsman.
     * @param craftsman the craftsman to be saved
     * @return the saved craftsman.
     */
    @PostMapping(value = "/addCraftsman")
    public Craftsman addCraftsman(@RequestBody Craftsman craftsman){

        return craftsmanService.addCraftsman(craftsman);
    }
}
