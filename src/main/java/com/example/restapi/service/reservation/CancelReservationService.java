package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CancelReservationService {

    private final ReservationRepository reservationRepository;
    private final FindReservationByIdService findReservationByIdService;

    public void cancel(long id) {
        Reservation reservation = findReservationByIdService.findById(id);

        if (ReservationStatus.CANCELLED.equals(reservation.getStatus()) || ReservationStatus.COMPLETED.equals(reservation.getStatus())) {
            throw new ConflictException("The reservation has already been cancelled or finalized.");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);

        reservationRepository.save(reservation);
    }

}
