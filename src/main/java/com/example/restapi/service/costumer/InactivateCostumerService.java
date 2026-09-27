package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.CostumerRepository;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.service.reservation.CancelAllReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class InactivateCostumerService {

    private final CostumerRepository costumerRepository;
    private final ReservationRepository reservationRepository;
    private final CancelAllReservationService cancelAllReservationService;
    private final FindCostumerByIdService findCostumerByIdService;

    public void inactivate(long id) {
        Costumer costumer = findCostumerByIdService.findById(id);

        if (!costumer.isActive()) {
            throw new ConflictException("Costumer are already inactivated");
        }

        List<Reservation> ongoingReservations = reservationRepository.findOngoingReservations(null, id, null, LocalDate.now());
        validateNoOngoingReservation(ongoingReservations);

        costumer.setActive(false);

        costumerRepository.save(costumer);
        cancelAllReservationService.cancelAll(null, id, null);
    }

    private void validateNoOngoingReservation(List<Reservation> reservations) {
        if (!reservations.isEmpty()) {
            throw new ConflictException(
                    "There are ongoing reservations; it is not possible to deactivate the costumer."
            );
        }
    }
}
