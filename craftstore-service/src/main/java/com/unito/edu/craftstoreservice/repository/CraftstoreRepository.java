package com.unito.edu.craftstoreservice.repository;

import com.unito.edu.craftstoreservice.model.Craftstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftstoreRepository extends JpaRepository<Craftstore,Integer>, JpaSpecificationExecutor<Craftstore> {
}
