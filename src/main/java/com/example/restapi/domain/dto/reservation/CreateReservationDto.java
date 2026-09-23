package com.example.restapi.domain.dto.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateReservationDto(
        @JsonProperty("costumer_id")
        @Schema(description = "Costumer ID", example = "1")
        @NotNull(message = "Costumer ID cannot be null.")
        Long costumerId,

        @JsonProperty("property_id")
        @Schema(description = "Porperty ID", example = "1")
        @NotNull(message = "Porperty ID cannot be null.")
        Long propertyId,

        @JsonProperty("check_in_date")
        @Schema(description = "Check in date", example = "2026-09-01")
        @NotNull(message = "Check in date cannot be null.")
        LocalDate checkInDate,

        @JsonProperty("check_out_date")
        @Schema(description = "Check out date", example = "2026-09-20")
        @NotNull(message = "Check out date cannot be null.")
        LocalDate checkOutDate,

        @JsonProperty("number_of_costumers")
        @Schema(description = "Number of costumers", example = "2")
        @NotNull(message = "Number of costumers cannot be null.")
        int numberOfCostumers
) { }
