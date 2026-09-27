package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.mock.entity.ReservationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CancelAllReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private CancelAllReservationService cancelAllReservationService;

    @Test
    void shouldCancelAllReservationsSuccessfully() {

        Reservation reservation = ReservationMock.reservationMock();

        Reservation reservation1 = ReservationMock.reservationMock();
        reservation.setStatus(ReservationStatus.CANCELLED);

        when(reservationRepository.findUpcomingReservations(any(Long.class),any(Long.class),any(Long.class),any(LocalDate.class)))
                .thenReturn(List.of(reservation));

        when(reservationRepository.saveAll(any(List.class)))
                .thenReturn(List.of(reservation1));

        cancelAllReservationService.cancelAll(1L, 1L, 1L);

        verify(reservationRepository, times(1)).findUpcomingReservations(any(Long.class),any(Long.class),any(Long.class),any(LocalDate.class));
        verify(reservationRepository, times(1)).saveAll(any(List.class));
    }

    @Test
    void shouldReturnWhenTheresNoPpcomingReservations() {
        when(reservationRepository.findUpcomingReservations(any(Long.class),any(Long.class),any(Long.class),any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        cancelAllReservationService.cancelAll(1L, 1L, 1L);

        verify(reservationRepository, times(1)).findUpcomingReservations(any(Long.class),any(Long.class),any(Long.class),any(LocalDate.class));
        verify(reservationRepository, times(0)).saveAll(any(List.class));
    }
}
