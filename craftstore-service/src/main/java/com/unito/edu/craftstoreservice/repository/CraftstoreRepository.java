package com.unito.edu.craftstoreservice.repository;

import com.unito.edu.craftstoreservice.model.Craftstore;
import com.unito.edu.craftstoreservice.model.dto.CraftstoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CraftstoreRepository extends JpaRepository<Craftstore,Integer>, JpaSpecificationExecutor<Craftstore> {
}
