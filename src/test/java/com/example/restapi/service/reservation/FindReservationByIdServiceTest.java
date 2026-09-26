package com.example.restapi.service.reservation;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.mock.entity.ReservationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindReservationByIdServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private FindReservationByIdService findReservationByIdService;

    @Test
    void shouldReturnReservationSuccessfully() {

        Reservation reservation = ReservationMock.reservationMock();

        when(reservationRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(reservation));

        Reservation result = findReservationByIdService.findById(1L);

        assertNotNull(result);

        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenReservationNotExists() {
        when(reservationRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> findReservationByIdService.findById(1L)
        );
    }
}
