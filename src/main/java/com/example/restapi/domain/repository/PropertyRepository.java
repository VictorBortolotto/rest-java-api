package com.example.restapi.domain.repository;

import com.example.restapi.domain.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {


    @Query("""
        SELECT p
          FROM Property p
         WHERE (:active IS NULL OR p.active = :active)
           AND (:id IS NULL OR p.id = :id)
           AND (:landLordId IS NULL OR p.landLord.id = :landLordId)
           AND (:avaliable IS NULL OR p.avaliable = :avaliable)
    """)
    List<Property> findAll(@Param("id") Long id, @Param("landLordId") Long landLordId, @Param("avaliable") Boolean isAvaliable, @Param("active") Boolean isActive);
}
