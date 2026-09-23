package com.example.restapi.domain.dto.landLord;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CreateRequestLandLordDto(
        @JsonProperty
        @Schema(description = "Land lord Name", example = "George")
        @NotEmpty(message = "Name cannot be null.")
        String name,

        @JsonProperty
        @Schema(description = "Land lord Document", example = "000-000-000.00")
        @NotEmpty(message = "Document cannot be null.")
        String document,

        @JsonProperty
        @Schema(description = "Land lord type", example = "HOTEL")
        @NotEmpty(message = "Land Lord type cannot be null.")
        String type,

        @JsonProperty
        @Schema(description = "Land lord Email", example = "test@teste.com.br")
        @NotEmpty(message = "Email cannot be null.")
        String email,

        @JsonProperty
        @Schema(description = "Land lord Phone", example = "+(00) 00 00000-0000")
        @NotEmpty(message = "Phone cannot be null.")
        String phone
) { }
