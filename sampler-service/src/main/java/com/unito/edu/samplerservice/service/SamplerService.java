package com.unito.edu.samplerservice.service;

import com.unito.edu.samplerservice.model.Product;
import com.unito.edu.samplerservice.model.Sampler;
import com.unito.edu.samplerservice.repository.ProductRepository;
import com.unito.edu.samplerservice.repository.SamplerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Sampler> getAllSamplers() {

        return samplerRepository.findAll();
    }

    /**
     * This method is used to get a sampler given its id.
     * @param id the sampler id
     * @return the sampler with that id
     */
    public Sampler getSamplerById(int id) {

        return samplerRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to save in the database a sampler.
     * @param sampler the sampler to be saved
     * @return the saved sampler
     */
    public Sampler addSampler(Sampler sampler){

        return samplerRepository.save(sampler);
    }

    /**
     * This method is used to get a product given its id.
     * @param id the product id
     * @return the product with that id
     */
    public Product getProductById(int id){

        return productRepository.findById(id).orElse(null);
    }

    /**
     * This method is used to add a product to a sampler.
     * @param id the sampler id
     * @param product the product to be added
     * @return the updated sampler
     */
    public Sampler addProduct(int id, Product product){

        Optional<Sampler> sampler = samplerRepository.findById(id);
        sampler.get().getProductList().add(product);
        return samplerRepository.save(sampler.get());
    }

    /**
     * This method is used to update a product.
     * @param id the product id
     * @param product the product as it should be
     * @return the updated product
     */
    public Product updateProduct(int id, Product product){

        Optional<Product> oldProduct = productRepository.findById(id);
        oldProduct.get().setDescription(product.getDescription());
        oldProduct.get().setPrice(product.getPrice());
        return productRepository.save(oldProduct.get());
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
    public void deleteProduct(int id){

        productRepository.deleteById(id);
    }
}
