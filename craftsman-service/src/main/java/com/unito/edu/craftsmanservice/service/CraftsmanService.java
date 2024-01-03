package com.unito.edu.craftsmanservice.service;

import com.unito.edu.craftsmanservice.model.Craftsman;
import com.unito.edu.craftsmanservice.repository.CraftsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CraftsmanService {

    @Autowired
    private CraftsmanRepository craftsmanRepository;

    /**
     * This method is used to get all craftsmen.
     *
     * @return a list of all craftsmen.
     */
    public List<Craftsman> getAllCraftsmen() {

        return craftsmanRepository.findAll();
    }

    /**
     * This method is used to get a craftsman given its id.
     * @param id the craftsman id
     * @return the craftsman with that id
     */
    public Craftsman getCraftsmanById(int id) {

        return craftsmanRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to save in the database a craftsman.
     * @param craftsman the craftsman to be saved
     * @return the saved craftsman.
     */
    public Craftsman addCraftsman(Craftsman craftsman){

        return craftsmanRepository.save(craftsman);
    }
}
