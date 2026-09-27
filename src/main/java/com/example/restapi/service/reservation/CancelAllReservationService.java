package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CancelAllReservationService {

    private final ReservationRepository reservationRepository;

    public void cancelAll(Long landLordId, Long costumerId, Long propertyId) {
        List<Reservation> upcomingReservations = reservationRepository.findUpcomingReservations(landLordId, costumerId, propertyId, LocalDate.now());

        if (upcomingReservations.isEmpty()) return;

        List<Reservation> cancelledReserves = new ArrayList<>();

        for (Reservation reservation : upcomingReservations) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            cancelledReserves.add(reservation);
        }

        reservationRepository.saveAll(cancelledReserves);
    }
}
