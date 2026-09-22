package com.example.restapi.domain.dto.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateReservationDto(
        @JsonProperty("costumer_id")
        @NotNull(message = "Costumer ID cannot be null.")
        Long costumerId,

        @JsonProperty("property_id")
        @NotNull(message = "Porperty ID cannot be null.")
        Long propertyId,

        @JsonProperty("check_in_date")
        @NotNull(message = "Check in date cannot be null.")
        LocalDate checkInDate,

        @JsonProperty("check_out_date")
        @NotNull(message = "Check out date cannot be null.")
        LocalDate checkOutDate,

        @JsonProperty("number_of_costumers")
        @NotNull(message = "Number of costumers cannot be null.")
        int numberOfCostumers
) { }
