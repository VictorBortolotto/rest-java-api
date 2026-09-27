package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.domain.repository.ReservationRepository;
import com.example.restapi.mock.entity.LandLordMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.mock.entity.ReservationMock;
import com.example.restapi.service.landLord.InactivateLandLordService;
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
public class InactivatePropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindPropertyByIdService findPropertyByIdService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CancelAllReservationService cancelAllReservationService;

    @InjectMocks
    private InactivatePropertyService inactivatePropertyService;

    @Test
    void shouldDeactivatePropertySuccessfully() {

        Property property = PropertyMock.propertyMock();

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(propertyRepository.save(any(Property.class)))
                .thenReturn(property);

        when(reservationRepository.findOngoingReservations(any(), any(), any(Long.class), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        inactivatePropertyService.inactivate(1L);

        verify(findPropertyByIdService, times(1)).findById(any(Long.class));
        verify(propertyRepository, times(1)).save(any(Property.class));
        verify(cancelAllReservationService, times(1)).cancelAll(any(), any(), any(Long.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenPropertyAreInactive() {

        Property property = PropertyMock.propertyMock();
        property.setActive(false);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        assertThrows(
                ConflictException.class,
                () -> inactivatePropertyService.inactivate(1L)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenOngoingReservation() {

        Property property = PropertyMock.propertyMock();
        Reservation reservation = ReservationMock.reservationMock();
        reservation.setCheckInDate(LocalDate.of(1900, 1, 1));
        reservation.setCheckOutDate(LocalDate.of(2900, 1, 1));

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(reservationRepository.findOngoingReservations(any(), any(), any(Long.class), any(LocalDate.class)))
                .thenReturn(List.of(reservation));

        assertThrows(
                ConflictException.class,
                () -> inactivatePropertyService.inactivate(1L)
        );
    }
}
