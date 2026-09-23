package com.example.restapi.domain.dto.costumer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CreateRequestCostumerDto(
        @JsonProperty
        @Schema(description = "Costumer Name", example = "Dalle")
        @NotEmpty(message = "Name cannot be null.")
        String name,

        @JsonProperty
        @Schema(description = "Costumer Document", example = "000-000-000.00")
        @NotEmpty(message = "Document cannot be null.")
        String document,

        @JsonProperty
        @Schema(description = "Costumer Email", example = "test@teste.com.br")
        @NotEmpty(message = "Email cannot be null.")
        String email,

        @JsonProperty
        @Schema(description = "Costumer Phone", example = "+(00) 00 00000-0000")
        @NotEmpty(message = "Phone cannot be null.")
        String phone
) {}
