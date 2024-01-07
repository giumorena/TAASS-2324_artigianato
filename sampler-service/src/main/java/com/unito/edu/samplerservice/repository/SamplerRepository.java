package com.unito.edu.samplerservice.repository;

import com.unito.edu.samplerservice.model.Sampler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamplerRepository extends JpaRepository<Sampler,Integer> {
}
