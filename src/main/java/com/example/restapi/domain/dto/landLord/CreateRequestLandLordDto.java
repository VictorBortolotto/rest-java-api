package com.example.restapi.domain.dto.landLord;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record CreateRequestLandLordDto(
        @JsonProperty
        @NotEmpty(message = "Name cannot be null.")
        String name,

        @JsonProperty
        @NotEmpty(message = "Document cannot be null.")
        String document,

        @JsonProperty
        @NotEmpty(message = "Land Lord type cannot be null.")
        String type,

        @JsonProperty
        @NotEmpty(message = "Email cannot be null.")
        String email,

        @JsonProperty
        @NotEmpty(message = "Phone cannot be null.")
        String phone
) { }
