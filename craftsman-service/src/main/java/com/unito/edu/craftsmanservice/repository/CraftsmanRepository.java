package com.unito.edu.craftsmanservice.repository;

import com.unito.edu.craftsmanservice.model.Craftsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman,Long> {
}
