package com.unito.edu.samplerservice.repository;

import com.unito.edu.samplerservice.model.Product;
import com.unito.edu.samplerservice.model.Sampler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
