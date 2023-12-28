package com.unito.edu.craftsmanservice.controller;

import com.unito.edu.craftsmanservice.model.Craftsman;
import com.unito.edu.craftsmanservice.repository.CraftsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CraftsmanController {

    @Autowired
    CraftsmanRepository craftsmanRepository;

    /**
     * This API is used to get all craftsmen.
     *
     * @return a list of all craftsmen.
     */
    @GetMapping("/getCraftsmen")
    public List<Craftsman> getAllCraftsmen() {

        return craftsmanRepository.findAll();
    }

    /**
     * This API is used to get a craftsman given its id.
     *
     * @return the craftsman with that id or null if not exists.
     */
    @GetMapping("/getCraftsman/{id}")
    public Craftsman getCraftsmanById(@PathVariable long id) {

        return craftsmanRepository.findById(id).orElse(null);
    }

    /**
     * This API is used to save in the database a craftsman.
     *
     * @return the saved craftsman.
     */
    @PostMapping(value = "/addCraftsman")
    public Craftsman postCraftsman(@RequestBody Craftsman craftsman){

        return craftsmanRepository.save(craftsman);
    }
}
