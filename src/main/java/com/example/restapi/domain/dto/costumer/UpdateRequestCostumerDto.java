package com.example.restapi.domain.dto.costumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public record UpdateRequestCostumerDto(
        @JsonProperty
        @NotEmpty(message = "Name cannot be null.")
        String name,

        @JsonProperty
        @NotEmpty(message = "Email cannot be null.")
        String email,

        @JsonProperty
        @NotEmpty(message = "Phone cannot be null.")
        String phone
) { }
