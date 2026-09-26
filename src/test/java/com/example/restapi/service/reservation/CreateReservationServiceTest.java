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
import com.example.restapi.mock.dto.reservation.CreateReservationDtoMock;
import com.example.restapi.mock.entity.CostumerMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.mock.entity.ReservationMock;
import com.example.restapi.service.costumer.FindCostumerByIdService;
import com.example.restapi.service.property.FindPropertyByIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FindCostumerByIdService costumerByIdService;

    @Mock
    private FindPropertyByIdService findPropertyByIdService;

    @InjectMocks
    private CreateReservationService createReservationService;

    @Test
    void shouldCreateReservationSuccessfully() {

        CreateReservationDto dto = CreateReservationDtoMock.createReservationDto();
        Reservation reservation = ReservationMock.reservationMock();
        Costumer costumer = CostumerMock.costumerMock();
        Property property = PropertyMock.propertyMock();

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(reservation);

        Reservation result = createReservationService.create(dto);

        assertNotNull(result);

        assertEquals(costumer.getName(), reservation.getCostumer().getName());
        assertEquals(property.getName(), reservation.getProperty().getName());

        assertEquals(dto.numberOfCostumers(), result.getNumberOfCostumers());
        assertEquals(dto.checkInDate(), result.getCheckInDate());
        assertEquals(dto.checkOutDate(), result.getCheckOutDate());
        assertEquals(reservation.getTotalAmount(), result.getTotalAmount());
        assertEquals(ReservationStatus.ACTIVE, result.getStatus());

        verify(costumerByIdService, times(1)).findById(any(Long.class));
        verify(findPropertyByIdService, times(1)).findById(any(Long.class));
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void shouldThrowInactivatedExceptionWhenCostumerAreInactivated() {

        CreateReservationDto dto = CreateReservationDtoMock.createReservationDto();
        Costumer costumer = CostumerMock.costumerMock();
        costumer.setActive(false);
        Property property = PropertyMock.propertyMock();

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        assertThrows(
                InactivatedException.class,
                () -> createReservationService.create(dto)
        );
    }

    @Test
    void shouldThrowInactivatedExceptionWhenPropertyAreInactivated() {

        CreateReservationDto dto = CreateReservationDtoMock.createReservationDto();
        Costumer costumer = CostumerMock.costumerMock();
        Property property = PropertyMock.propertyMock();
        property.setActive(false);

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        assertThrows(
                InactivatedException.class,
                () -> createReservationService.create(dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenHavesAnotherReservationInTheSamePeriod() {

        CreateReservationDto dto = CreateReservationDtoMock.createReservationDto();
        Reservation reservation = ReservationMock.reservationMock();
        reservation.setId(1L);

        Costumer costumer = CostumerMock.costumerMock();
        Property property = PropertyMock.propertyMock();

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(reservationRepository.findReservations(any(Long.class),any(LocalDate.class),any(LocalDate.class)))
                .thenReturn(List.of(reservation));

        assertThrows(
                ConflictException.class,
                () -> createReservationService.create(dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenCheckoutDateIsBeforeCheckinDate() {

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                1L,
                LocalDate.of(2026,9,1),
                LocalDate.of(2026,8,20),
                2
        );

        Costumer costumer = CostumerMock.costumerMock();
        Property property = PropertyMock.propertyMock();

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(reservationRepository.findReservations(any(Long.class),any(LocalDate.class),any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        assertThrows(
                ConflictException.class,
                () -> createReservationService.create(dto)
        );
    }

    @Test
    void shouldThrowInsufficentValueExcpetionWhenNumberOfCostumerEqualsZero() {

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                1L,
                LocalDate.of(2026,9,1),
                LocalDate.of(2026,9,20),
                0
        );

        Costumer costumer = CostumerMock.costumerMock();
        Property property = PropertyMock.propertyMock();

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(reservationRepository.findReservations(any(Long.class),any(LocalDate.class),any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        assertThrows(
                InsufficentValueExcpetion.class,
                () -> createReservationService.create(dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenNumberOfCostumerExceedPropertyCapacity() {

        CreateReservationDto dto = new CreateReservationDto(
                1L,
                1L,
                LocalDate.of(2026,9,1),
                LocalDate.of(2026,9,20),
                5
        );

        Costumer costumer = CostumerMock.costumerMock();
        Property property = PropertyMock.propertyMock();

        when(costumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(reservationRepository.findReservations(any(Long.class),any(LocalDate.class),any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        assertThrows(
                ConflictException.class,
                () -> createReservationService.create(dto)
        );
    }
}
