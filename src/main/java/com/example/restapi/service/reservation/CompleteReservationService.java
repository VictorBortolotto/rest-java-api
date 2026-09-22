package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompleteReservationService {

    private final ReservationRepository reservationRepository;
    private final FindReservationByIdService findReservationByIdService;

    public void complete(long id) {
        Reservation reservation = findReservationByIdService.findById(id);

        if (!ReservationStatus.ACTIVE.equals(reservation.getStatus())) {
            throw new ConflictException("To finalize the reservation, it needs to be active.");
        }

        reservation.setStatus(ReservationStatus.COMPLETED);

        reservationRepository.save(reservation);
    }
}
