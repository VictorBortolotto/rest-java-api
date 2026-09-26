package com.example.restapi.domain.repository;

import com.example.restapi.domain.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    @Query("""
        SELECT c
          FROM Costumer c
         WHERE :active IS NULL
            OR c.active = :active
    """)
    List<Costumer> findAll(@Param("active") Boolean active);

    Optional<Costumer> findByDocument(@Param("document") String document);
    Optional<Costumer> findByEmail(@Param("email") String email);
    Optional<Costumer> findByEmailAndIdNot(@Param("email") String email, @Param("id") long id);
}
