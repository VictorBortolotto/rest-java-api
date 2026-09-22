package com.example.restapi.domain.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegistryPropertyDto(

        @JsonProperty("land_lord_id")
        @NotNull(message = "Landlord cannot be null.")
        long landLordId,

        @JsonProperty
        @NotEmpty(message = "Name cannot be null.")
        String name,

        @JsonProperty("property_type")
        @NotEmpty(message = "Porperty Type cannot be null.")
        String propertyType,

        @JsonProperty
        @NotEmpty(message = "Address cannot be null.")
        String address,

        @JsonProperty
        @NotEmpty(message = "Number cannot be null.")
        String number,

        @JsonProperty
        @NotEmpty(message = "Neighborhood cannot be null.")
        String neighborhood,

        @JsonProperty
        @NotEmpty(message = "City cannot be null.")
        String city,

        @JsonProperty
        @NotEmpty(message = "State cannot be null.")
        String state,

        @JsonProperty("zip_code")
        @NotEmpty(message = "Zip Code cannot be null.")
        String zipCode,

        @JsonProperty
        @NotNull(message = "Capacity cannot be null.")
        int capacity,

        @JsonProperty("daily_rate")
        @NotNull(message = "Daily rate cannot be null.")
        int dailyRate,

        @JsonProperty
        String notes
) { }
