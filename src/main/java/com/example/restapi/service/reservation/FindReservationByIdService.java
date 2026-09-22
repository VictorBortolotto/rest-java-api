package com.example.restapi.service.reservation;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindReservationByIdService {

    private final ReservationRepository reservationRepository;

    public Reservation findById(long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
    }
}
