package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findAll(Long idClient, Long idProperty, ReservationStatus status) {
        List<Reservation> reservations = reservationRepository.findAll(idClient, idProperty, status);

        if (reservations.isEmpty()) {
            throw new NotFoundException("Reservations not found.");
        }

        return reservations;
    }
}
