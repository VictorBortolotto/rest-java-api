package com.example.restapi.service.reservation;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.mock.entity.ReservationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private FindAllReservationService findAllReservationService;

    @Test
    void shouldReturnReservationsSuccessfully() {

        Reservation reservation = ReservationMock.reservationMock();

        when(reservationRepository.findAll(any(Long.class),any(Long.class),any(ReservationStatus.class)))
                .thenReturn(List.of(reservation));

        List<Reservation> result = findAllReservationService.findAll(1L, 1L, ReservationStatus.ACTIVE);

        assertNotNull(result);

        verify(reservationRepository, times(1)).findAll(any(Long.class),any(Long.class),any(ReservationStatus.class));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenReservationNotExists() {
        when(reservationRepository.findAll(any(Long.class),any(Long.class),any(ReservationStatus.class)))
                .thenReturn(Collections.emptyList());

        assertThrows(
                NotFoundException.class,
                () -> findAllReservationService.findAll(1L, 1L, ReservationStatus.ACTIVE)
        );
    }

}
