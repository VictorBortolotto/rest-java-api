package com.example.restapi.domain.repository;

import com.example.restapi.domain.model.LandLord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandLordRepository extends JpaRepository<LandLord, Long> {

    Optional<?> findByDocument(@Param("document") String document);
    Optional<?> findByEmail(@Param("email") String email);
    Optional<?> findByEmailAndIdNot(@Param("email") String email, @Param("id") long id);
}
