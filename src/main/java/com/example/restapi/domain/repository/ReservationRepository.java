package com.example.restapi.domain.repository;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        SELECT r
          FROM Reservation r
         WHERE r.property.id = :propertyId
           AND r.checkInDate < :checkOutDate
           AND r.checkOutDate > :checkInDate
           AND r.status = "ACTIVE"
    """)
    List<Reservation> findReservations(@Param("propertyId") long propertyId,
                                       @Param("checkInDate") LocalDate checkInDate,
                                       @Param("checkOutDate") LocalDate checkOutDate);

    @Query("""
        SELECT r
          FROM Reservation r
         WHERE (:landLordId IS NULL OR r.property.landLord.id = :landLordId)
           AND (:costumerId IS NULL OR r.costumer.id = :costumerId)
           AND (:propertyId IS NULL OR r.property.id = :propertyId)
           AND r.status = 'ACTIVE'
           AND r.checkInDate <= :date
           AND r.checkOutDate > :date
    """)
    List<Reservation> findOngoingReservations(
            @Param("landLordId") Long landLordId,
            @Param("costumerId") Long costumerId,
            @Param("propertyId") Long propertyId,
            @Param("date") LocalDate date
    );

    @Query("""
        SELECT r
          FROM Reservation r
         WHERE (:landLordId IS NULL OR r.property.landLord.id = :landLordId)
           AND (:costumerId IS NULL OR r.costumer.id = :costumerId)
           AND (:propertyId IS NULL OR r.property.id = :propertyId)
           AND r.status = 'ACTIVE'
           AND r.checkInDate > :date
    """)
    List<Reservation> findUpcomingReservations(
            @Param("landLordId") Long landLordId,
            @Param("costumerId") Long costumerId,
            @Param("propertyId") Long propertyId,
            @Param("date") LocalDate date
    );

    @Query("""
        SELECT r
          FROM Reservation r
         WHERE (:idClient IS NULL OR r.costumer.id = :idClient)
           AND (:idProperty IS NULL OR r.property.id = :idProperty)
           AND (:status IS NULL OR r.status = :status)
    """)
    List<Reservation> findAll(@Param("idClient") Long idClient,
                              @Param("idProperty") Long idProperty,
                              @Param("status") ReservationStatus status);
}
