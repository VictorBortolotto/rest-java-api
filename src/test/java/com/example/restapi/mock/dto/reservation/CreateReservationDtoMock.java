package com.example.restapi.mock.dto.reservation;

import com.example.restapi.domain.dto.reservation.CreateReservationDto;

import java.time.LocalDate;

public class CreateReservationDtoMock {
    public static CreateReservationDto createReservationDto() {
        return new CreateReservationDto(
                1L,
                1L,
                LocalDate.of(2026,9,1),
                LocalDate.of(2026,9,20),
                2
        );
    }

}
