package com.unito.edu.craftsmanservice.service;

import com.unito.edu.craftsmanservice.model.Craftsman;
import com.unito.edu.craftsmanservice.model.Ownership;
import com.unito.edu.craftsmanservice.repository.CraftsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CraftsmanService {

    @Autowired
    private CraftsmanRepository craftsmanRepository;

    /**
     * This method is used to get all craftsmen.
     *
     * @return a list of all craftsmen.
     */
    public ResponseEntity<?> getAllCraftsmen() {

        List<Craftsman> craftsmen = craftsmanRepository.findAll();

        if(craftsmen.size()>0){
            return new ResponseEntity<>(craftsmen,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(craftsmen,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get a craftsman given his id.
     * @param id the craftsman id
     * @return the craftsman with that id
     */
    public ResponseEntity<?> getCraftsmanById(int id) {

        Optional<Craftsman> craftsman = craftsmanRepository.findById(id);

        if(craftsman.isPresent()) {
            return new ResponseEntity<>(craftsman,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get a craftsman given his email.
     * @param email the craftsman's email
     * @return the craftsman with that email
     */
    public ResponseEntity<?> getCraftsmanByEmail(String email) {

        Optional<Craftsman> craftsman = craftsmanRepository.findByEmail(email);

        if(craftsman.isPresent()){
            return new ResponseEntity<>(craftsman, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get all craftstores owned by a craftsman given his id.
     * @param id the craftsman id
     * @return the craftstores owned by the craftsman with that id
     */
    public ResponseEntity<?> getCraftstoresByCraftsmanId(int id) {

        Optional<Craftsman> craftsman = craftsmanRepository.findById(id);

        if(craftsman.isPresent()) {
            return new ResponseEntity<>(craftsman.get().getCraftstoreList(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to save in the database a craftsman.
     * @param craftsman the craftsman to be saved
     * @return the saved craftsman.
     */
    public ResponseEntity<?> addCraftsman(Craftsman craftsman) {

        try {
            return new ResponseEntity<>(craftsmanRepository.save(craftsman),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
