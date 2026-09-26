package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.mock.entity.ReservationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CancelReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FindReservationByIdService findReservationByIdService;

    @InjectMocks
    private CancelReservationService cancelReservationService;

    @Test
    void shouldCancelReservationSuccessfully() {

        Reservation reservation = ReservationMock.reservationMock();

        when(findReservationByIdService.findById(any(Long.class)))
                .thenReturn(reservation);

        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(reservation);

        cancelReservationService.cancel(1L);

        verify(findReservationByIdService, times(1)).findById(any(Long.class));
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenReservationIsAlreadyCancelled() {
        Reservation reservation = ReservationMock.reservationMock();
        reservation.setStatus(ReservationStatus.CANCELLED);

        when(findReservationByIdService.findById(any(Long.class)))
                .thenReturn(reservation);

        assertThrows(
                ConflictException.class,
                () -> cancelReservationService.cancel(1L)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenReservationIsAlreadyCompleted() {
        Reservation reservation = ReservationMock.reservationMock();
        reservation.setStatus(ReservationStatus.COMPLETED);

        when(findReservationByIdService.findById(any(Long.class)))
                .thenReturn(reservation);

        assertThrows(
                ConflictException.class,
                () -> cancelReservationService.cancel(1L)
        );
    }
}
