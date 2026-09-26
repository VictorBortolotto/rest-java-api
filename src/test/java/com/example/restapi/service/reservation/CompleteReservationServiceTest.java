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
public class CompleteReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FindReservationByIdService findReservationByIdService;

    @InjectMocks
    private CompleteReservationService completeReservationService;

    @Test
    void shouldCompleteReservationSuccessfully() {

        Reservation reservation = ReservationMock.reservationMock();

        when(findReservationByIdService.findById(any(Long.class)))
                .thenReturn(reservation);

        when(reservationRepository.save(any(Reservation.class)))
                .thenReturn(reservation);

        completeReservationService.complete(1L);

        verify(findReservationByIdService, times(1)).findById(any(Long.class));
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenReservationStatusIsNotEqualsActive() {
        Reservation reservation = ReservationMock.reservationMock();
        reservation.setStatus(ReservationStatus.CANCELLED);

        when(findReservationByIdService.findById(any(Long.class)))
                .thenReturn(reservation);

        assertThrows(
                ConflictException.class,
                () -> completeReservationService.complete(1L)
        );
    }
}
