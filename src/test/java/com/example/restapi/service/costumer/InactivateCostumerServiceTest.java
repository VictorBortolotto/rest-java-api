package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.CostumerRepository;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.mock.entity.CostumerMock;
import com.example.restapi.mock.entity.ReservationMock;
import com.example.restapi.service.reservation.CancelAllReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InactivateCostumerServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @Mock
    private FindCostumerByIdService findCostumerByIdService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CancelAllReservationService cancelAllReservationService;

    @InjectMocks
    private InactivateCostumerService inactivateCostumerService;

    @Test
    void shouldDeactivateCostumerSuccessfully() {
        Costumer costumer = CostumerMock.costumerMock();

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(costumerRepository.save(any(Costumer.class)))
                .thenReturn(costumer);

        when(reservationRepository.findOngoingReservations(any(), any(Long.class), any(), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        inactivateCostumerService.inactivate(1L);

        verify(findCostumerByIdService, times(1)).findById(any(Long.class));
        verify(costumerRepository, times(1)).save(any(Costumer.class));
        verify(cancelAllReservationService, times(1)).cancelAll(any(), any(Long.class), any());
    }

    @Test
    void shouldThrowConflictExceptionWhenCostumerAreInactive() {

        Costumer costumer = CostumerMock.costumerMock();
        costumer.setActive(false);

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        assertThrows(
                ConflictException.class,
                () -> inactivateCostumerService.inactivate(1L)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenOngoingReservation() {

        Costumer costumer = CostumerMock.costumerMock();
        Reservation reservation = ReservationMock.reservationMock();
        reservation.setCheckInDate(LocalDate.of(1900, 1, 1));
        reservation.setCheckOutDate(LocalDate.of(2900, 1, 1));

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);


        when(reservationRepository.findOngoingReservations(any(), any(Long.class), any(), any(LocalDate.class)))
                .thenReturn(List.of(reservation));


        assertThrows(
                ConflictException.class,
                () -> inactivateCostumerService.inactivate(1L)
        );
    }
}
