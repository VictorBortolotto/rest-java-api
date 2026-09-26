package com.example.restapi.controller.reservation;

import com.example.restapi.domain.dto.reservation.CreateReservationDto;
import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.mock.dto.reservation.CreateReservationDtoMock;
import com.example.restapi.mock.entity.ReservationMock;
import com.example.restapi.service.reservation.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private CreateReservationService createReservationService;

    @Mock
    private FindReservationByIdService findReservationByIdService;

    @Mock
    private FindAllReservationService findAllReservationService;

    @Mock
    private CompleteReservationService completeReservationService;

    @Mock
    private CancelReservationService cancelReservationService;

    @InjectMocks
    private ReservationController reservationController;


    @Test
    void shouldCreateReservation() {

        CreateReservationDto dto = CreateReservationDtoMock.createReservationDto();

        Reservation reservation = ReservationMock.reservationMock();

        when(createReservationService.create(dto))
                .thenReturn(reservation);

        Reservation result =
                reservationController.create(dto);

        assertNotNull(result);
        assertEquals(reservation, result);

        verify(createReservationService)
                .create(dto);
    }


    @Test
    void shouldFindReservationById() {

        long id = 1L;

        Reservation reservation = new Reservation();

        when(findReservationByIdService.findById(id))
                .thenReturn(reservation);

        Reservation result =
                reservationController.findById(id);

        assertNotNull(result);
        assertEquals(reservation, result);

        verify(findReservationByIdService)
                .findById(id);
    }


    @Test
    void shouldFindAllReservations() {

        Long id = 1L;
        Long clientId = 2L;
        Long propertyId = 3L;

        ReservationStatus status =
                ReservationStatus.COMPLETED;

        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();

        List<Reservation> reservations =
                List.of(
                        reservation1,
                        reservation2
                );

        when(findAllReservationService.findAll(
                clientId,
                propertyId,
                status
        )).thenReturn(reservations);

        List<Reservation> result =
                reservationController.findAll(
                        id,
                        clientId,
                        propertyId,
                        status
                );

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(reservations, result);

        verify(findAllReservationService)
                .findAll(
                        clientId,
                        propertyId,
                        status
                );
    }


    @Test
    void shouldFindAllReservationsWithoutFilters() {

        when(findAllReservationService.findAll(
                null,
                null,
                null
        )).thenReturn(List.of());

        List<Reservation> result =
                reservationController.findAll(
                        null,
                        null,
                        null,
                        null
                );

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(findAllReservationService)
                .findAll(
                        null,
                        null,
                        null
                );
    }


    @Test
    void shouldCancelReservation() {

        long id = 1L;

        reservationController.cancel(id);

        verify(cancelReservationService)
                .cancel(id);
    }


    @Test
    void shouldCompleteReservation() {

        long id = 1L;

        reservationController.complete(id);

        verify(completeReservationService)
                .complete(id);
    }
}