package com.example.restapi.domain.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegistryPropertyDto(

        @JsonProperty("land_lord_id")
        @Schema(description = "Land Lord ID", example = "1")
        @NotNull(message = "Landlord cannot be null.")
        long landLordId,

        @JsonProperty
        @Schema(description = "Property name", example = "National Res.")
        @NotEmpty(message = "Name cannot be null.")
        String name,

        @JsonProperty("property_type")
        @Schema(description = "Property type", example = "ROOM")
        @NotEmpty(message = "Porperty Type cannot be null.")
        String propertyType,

        @JsonProperty
        @Schema(description = "Property address", example = "1234 Main Street")
        @NotEmpty(message = "Address cannot be null.")
        String address,

        @JsonProperty
        @Schema(description = "Property number", example = "1234")
        @NotEmpty(message = "Number cannot be null.")
        String number,

        @JsonProperty
        @Schema(description = "Property neighborhood", example = "Center")
        @NotEmpty(message = "Neighborhood cannot be null.")
        String neighborhood,

        @JsonProperty
        @Schema(description = "Property city", example = "Springfield")
        @NotEmpty(message = "City cannot be null.")
        String city,

        @JsonProperty
        @Schema(description = "Property state", example = "Massachusetts")
        @NotEmpty(message = "State cannot be null.")
        String state,

        @JsonProperty("zip_code")
        @Schema(description = "Property Zip Code", example = "10001")
        @NotEmpty(message = "Zip Code cannot be null.")
        String zipCode,

        @JsonProperty
        @Schema(description = "Property capacity", example = "4")
        @NotNull(message = "Capacity cannot be null.")
        int capacity,

        @JsonProperty("daily_rate")
        @Schema(description = "Property daily rate", example = "4.90")
        @NotNull(message = "Daily rate cannot be null.")
        int dailyRate,

        @JsonProperty
        @Schema(description = "Property notes", example = "Windows with tinted glass")
        String notes
) { }
