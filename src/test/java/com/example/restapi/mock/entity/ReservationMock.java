package com.example.restapi.mock.entity;

import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.model.Reservation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationMock {

    public static Reservation reservationMock() {
        Reservation reservation = new Reservation();
        Property property = PropertyMock.propertyMock();

        reservation.setId(1L);
        reservation.setCostumer(CostumerMock.costumerMock());
        reservation.setProperty(property);
        reservation.setNumberOfCostumers(2);
        reservation.setCheckInDate(LocalDate.of(2026,9,1));
        reservation.setCheckOutDate(LocalDate.of(2026,9,20));
        reservation.setTotalAmount(calculateTotalAmount(property));
        reservation.setStatus(ReservationStatus.ACTIVE);

        return reservation;
    }

    private static double calculateTotalAmount(Property property) {
        long totalDaysReserve = ChronoUnit.DAYS.between(LocalDate.of(2026,9,1), LocalDate.of(2026,9,20));

        return totalDaysReserve * property.getDailyRate();
    }
}
