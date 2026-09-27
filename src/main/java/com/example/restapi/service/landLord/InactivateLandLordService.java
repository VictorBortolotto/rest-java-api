package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.service.property.FindAllPropertyService;
import com.example.restapi.service.reservation.CancelAllReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InactivateLandLordService {

    private final LandLordRepository landLordRepository;
    private final PropertyRepository propertyRepository;
    private final ReservationRepository reservationRepository;
    private final CancelAllReservationService cancelAllReservationService;
    private final FindLandLordByIdService findLandLordByIdService;
    private final FindAllPropertyService findAllPropertyService;

    public void inactivate(long id) {
        LandLord landLord = findLandLordByIdService.findById(id);

        if (!landLord.isActive()) {
            throw new ConflictException("Landlord are already inactivated");
        }

        List<Reservation> reservations = reservationRepository.findOngoingReservations(id, null, null, LocalDate.now());
        validateNoOngoingReservation(reservations);

        landLord.setActive(false);

        landLordRepository.save(landLord);
        inactivateAllActivePropertiesByLandLordId(landLord.getId());
        cancelAllReservationService.cancelAll(id, null, null);
    }

    private void inactivateAllActivePropertiesByLandLordId(long idLandLord) {
        List<Property> properties = findAllPropertyService.findAll(null, idLandLord, null, true);

        if (properties.isEmpty()) {
            return;
        }

        List<Property> unactivatedProperties = new ArrayList<>();

        for (Property property : properties) {
            property.setActive(false);
            unactivatedProperties.add(property);
        }

        propertyRepository.saveAll(unactivatedProperties);
    }

    private void validateNoOngoingReservation(List<Reservation> reservations) {
        if (!reservations.isEmpty()) {
            throw new ConflictException(
                    "There are ongoing reservations; it is not possible to deactivate the renter."
            );
        }
    }
}
