package com.unito.edu.samplerservice.service;

import com.unito.edu.samplerservice.model.Product;
import com.unito.edu.samplerservice.model.Sampler;
import com.unito.edu.samplerservice.repository.ProductRepository;
import com.unito.edu.samplerservice.repository.SamplerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SamplerService {

    @Autowired
    private SamplerRepository samplerRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method is used to get all samplers.
     *
     * @return a list of all samplers.
     */
    public ResponseEntity<?> getAllSamplers() {

        List<Sampler> samplers = samplerRepository.findAll();

        if(samplers.size()>0){
            return new ResponseEntity<>(samplers, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(samplers, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to get a sampler given its id.
     * @param id the sampler id
     * @return the sampler with that id
     */
    public ResponseEntity<?> getSamplerById(int id) {

        Optional<Sampler> sampler = samplerRepository.findById(id);

        if(sampler.isPresent()){
            return new ResponseEntity<>(sampler,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to save in the database a sampler.
     * @param sampler the sampler to be saved
     * @return the saved sampler
     */
    public ResponseEntity<?> addSampler(Sampler sampler){

        try {
            return new ResponseEntity<>(samplerRepository.save(sampler),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is used to get a product given its id.
     * @param id the product id
     * @return the product with that id
     */
    public ResponseEntity<?> getProductById(int id){

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to add a product to a sampler.
     * @param id the sampler id
     * @param product the product to be added
     * @return the updated sampler
     */
    public ResponseEntity<?> addProduct(int id, Product product){

        Optional<Sampler> sampler = samplerRepository.findById(id);

        if(sampler.isPresent()) {
            sampler.get().getProductList().add(product);
            try {
                return new ResponseEntity<>(samplerRepository.save(sampler.get()),HttpStatus.OK);
            }
            catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is used to update a product.
     * @param id the product id
     * @param product the product as it should be
     * @return the updated product
     */
    public ResponseEntity<?> updateProduct(int id, Product product){

        Optional<Product> oldProduct = productRepository.findById(id);

        if(oldProduct.isPresent()) {
            oldProduct.get().setDescription(product.getDescription());
            oldProduct.get().setPrice(product.getPrice());
            try {
                return new ResponseEntity<>(productRepository.save(oldProduct.get()),HttpStatus.OK);
            }
            catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * This method is used to delete a product from a sampler.
     * @param samplerId the sampler id
     * @param productId the product to be deleted
     * @return the updated sampler
     */
    /*public Sampler deleteProduct(int samplerId, int productId){

        Optional<Sampler> sampler = samplerRepository.findById(samplerId);
        int i=0;
        while(i<sampler.get().getProductList().size() && sampler.get().getProductList().get(i).getId()!=productId)
            i++;
        sampler.get().getProductList().remove(i);
        return samplerRepository.save(sampler.get());
    }
    */

    /**
     * This method is used to delete a product.
     * @param id the product id
     */
    public ResponseEntity<?> deleteProduct(int id){

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            try {
                productRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
