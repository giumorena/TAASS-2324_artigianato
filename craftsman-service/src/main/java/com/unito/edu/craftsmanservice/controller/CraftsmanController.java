package com.unito.edu.craftsmanservice.controller;

import com.unito.edu.craftsmanservice.model.Craftsman;
import com.unito.edu.craftsmanservice.model.Ownership;
import com.unito.edu.craftsmanservice.service.CraftsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/craftsman")
public class CraftsmanController {

    @Autowired
    private CraftsmanService craftsmanService;

    /**
     * This API is used to get all craftsmen.
     *
     * @return a list of all craftsmen.
     */
    @GetMapping("/getCraftsmen")
    public ResponseEntity<?> getAllCraftsmen() {

        return craftsmanService.getAllCraftsmen();
    }

    /**
     * This API is used to get a craftsman given his id.
     * @param id the craftsman id
     * @return the craftsman with that id
     */
    @GetMapping("/getCraftsman/{id}")
    public ResponseEntity<?> getCraftsmanById(@PathVariable int id) {

        return craftsmanService.getCraftsmanById(id);
    }

    /**
     * This API is used to get a craftsman given his email.
     * @param email the craftsman's email
     * @return the craftsman with that email
     */
    @GetMapping("/getCraftsmanByEmail/{email}")
    public ResponseEntity<?> getCraftsmanByEmail(@PathVariable String email) {

        return craftsmanService.getCraftsmanByEmail(email);
    }

    /**
     * This API is used to get all craftstores owned by a craftsman given his id.
     * @param id the craftsman id
     * @return the craftstores owned by the craftsman with that id
     */
    @GetMapping("/getCraftstores/{id}")
    public ResponseEntity<?> getCraftstoresByCraftsmanId(@PathVariable int id) {

        return craftsmanService.getCraftstoresByCraftsmanId(id);
    }

    /**
     * This method is used to save in the database a craftsman.
     * @param craftsman the craftsman to be saved
     * @return the saved craftsman.
     */
    @PostMapping(value = "/addCraftsman")
    public ResponseEntity<?> addCraftsman(@RequestBody Craftsman craftsman){

        return craftsmanService.addCraftsman(craftsman);
    }
}
