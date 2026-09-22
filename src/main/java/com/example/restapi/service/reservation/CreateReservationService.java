package com.example.restapi.service.reservation;

import com.example.restapi.domain.dto.reservation.CreateReservationDto;
import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.exceptions.InsufficentValueExcpetion;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.service.costumer.FindCostumerByIdService;
import com.example.restapi.service.property.FindPropertyByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateReservationService {

    private final ReservationRepository reservationRepository;
    private final FindCostumerByIdService costumerByIdService;
    private final FindPropertyByIdService findPropertyByIdService;

    public Reservation create(CreateReservationDto createReservationDto) {
        Costumer costumer = costumerByIdService.findById(createReservationDto.costumerId());
        Property property = findPropertyByIdService.findById(createReservationDto.propertyId());

        validateReservation(createReservationDto, costumer, property);

        long totalDaysReserve = ChronoUnit.DAYS.between(createReservationDto.checkInDate(), createReservationDto.checkOutDate());

        double totalAmount = totalDaysReserve * property.getDailyRate();

        Reservation reservation = new Reservation();
        reservation.setCostumer(costumer);
        reservation.setProperty(property);
        reservation.setCheckInDate(createReservationDto.checkInDate());
        reservation.setCheckOutDate(createReservationDto.checkOutDate());
        reservation.setNumberOfCostumers(createReservationDto.numberOfCostumers());
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.setTotalAmount(totalAmount);

        return reservationRepository.save(reservation);
    }

    private void validateReservation(
            CreateReservationDto dto,
            Costumer costumer,
            Property property
    ) {
        if (!costumer.isActive()) {
            throw new InactivatedException("Inactivated costumer.");
        }

        if (!property.isActive()) {
            throw new InactivatedException("Inactivated property.");
        }

        List<Reservation> reservations = reservationRepository.findReservations(property.getId(), dto.checkInDate(), dto.checkOutDate());

        if (!reservations.isEmpty()) {
            throw new ConflictException("Reservation conflicts with other reservations for the same period.");
        }

        if (!dto.checkInDate().isBefore(dto.checkOutDate())) {
            throw new ConflictException("The check-out date must be later than the check-in date.");
        }

        if (dto.numberOfCostumers() <= 0) {
            throw new InsufficentValueExcpetion("The number of guests must be greater than zero.");
        }

        if (dto.numberOfCostumers() > property.getCapacity()) {
            throw new ConflictException("The number of guests cannot exceed the property's capacity.");
        }
    }
}
