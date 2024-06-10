package com.unito.edu.samplerservice.controller;

import com.unito.edu.samplerservice.model.Product;
import com.unito.edu.samplerservice.model.Sampler;
import com.unito.edu.samplerservice.service.SamplerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sampler")
public class SamplerController {

    @Autowired
    private SamplerService samplerService;

    /**
     * This API is used to get all samplers.
     *
     * @return a list of all samplers.
     */
    @GetMapping("/getSamplers")
    public List<Sampler> getAllSamplers() {

        return samplerService.getAllSamplers();
    }

    /**
     * This API is used to get a sampler given its id.
     * @param id the sampler id
     * @return the sampler with that id
     */
    @GetMapping("/getSampler/{id}")
    public Sampler getSamplerById(@PathVariable int id) {

        return samplerService.getSamplerById(id);
    }

    /**
     * This API is used to save in the database a sampler.
     * @param sampler the sampler to be saved
     * @return the saved sampler
     */
    @PostMapping(value = "/addSampler")
    public Sampler addSampler(@RequestBody Sampler sampler){

        return samplerService.addSampler(sampler);
    }

    /**
     * This API is used to get a product given its id.
     * @param id the product id
     * @return the product with that id
     */
    @GetMapping("/getProduct/{id}")
    public Product getProductById(@PathVariable int id) {

        return samplerService.getProductById(id);
    }

    /**
     * This API is used to add a product to a sampler.
     * @param id the sampler id
     * @param product the product to be added
     * @return the updated sampler
     */
    @PostMapping(value = "/addProduct/{id}")
    public Sampler addProduct(@PathVariable int id,
                              @RequestBody Product product){

        return samplerService.addProduct(id,product);
    }

    /**
     * This API is used to update a product.
     * @param id the product id
     * @param product the product as it should be
     * @return the updated product
     */
    @PutMapping(value = "/updateProduct/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @RequestBody Product product){

        return samplerService.updateProduct(id,product);
    }

    /**
     * This API is used to delete a product.
     * @param id the product id
     */
    @DeleteMapping(value = "/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id){

        samplerService.deleteProduct(id);
    }
}
