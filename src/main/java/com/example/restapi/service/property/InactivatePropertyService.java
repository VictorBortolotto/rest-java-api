package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.service.reservation.CancelAllReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class InactivatePropertyService {

    private final PropertyRepository propertyRepository;
    private final ReservationRepository reservationRepository;
    private final CancelAllReservationService cancelAllReservationService;
    private final FindPropertyByIdService findPropertyByIdService;

    public void inactivate(long id) {
        Property property = findPropertyByIdService.findById(id);

        if (!property.isActive()) {
            throw new ConflictException("Property are already inactivated.");
        }

        List<Reservation> reservations = reservationRepository.findOngoingReservations(null, null, id, LocalDate.now());
        validateNoOngoingReservation(reservations);

        property.setActive(false);

        propertyRepository.save(property);
        cancelAllReservationService.cancelAll(null, null, id);
    }

    private void validateNoOngoingReservation(List<Reservation> reservations) {
        if (!reservations.isEmpty()) {
            throw new ConflictException(
                    "There are ongoing reservations; it is not possible to deactivate the property."
            );
        }
    }
}
