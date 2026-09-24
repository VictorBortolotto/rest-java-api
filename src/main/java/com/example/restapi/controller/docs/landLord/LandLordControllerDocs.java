package com.example.restapi.controller.docs.landLord;

import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;
import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.model.LandLord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface LandLordControllerDocs {

    @Operation(
            summary = "Create land lord.",
            description = "Create a new land lord."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Land lord created with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                               value = """
                                    {
                                      "id": 1,
                                      "name": "George",
                                      "document": "000-000-000.00",
                                      "landLordType": "HOTEL",
                                      "email": "test@teste.com.br",
                                      "phone": "+(00) 00 00000-0000",
                                      "active": true
                                    }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Land lord already exists with email or document",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land lord already exists with email or document.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LandLord create(
            @Valid @RequestBody CreateRequestLandLordDto createRequestLandLordDto
    );

    @Operation(
            summary = "Find all Land lords.",
            description = "Find all Land lords."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Land lords successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                               value = """
                                [
                                    {
                                      "id": 1,
                                      "name": "George",
                                      "document": "000-000-000.00",
                                      "landLordType": "HOTEL",
                                      "email": "test@teste.com.br",
                                      "phone": "+(00) 00 00000-0000",
                                      "active": true
                                    }
                                ]
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land lords not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land lords not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<LandLord> findAll();

    @Operation(
            summary = "Find Land lord by ID.",
            description = "Find Land lord by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Land lord successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                               value = """
                                    {
                                      "id": 1,
                                      "name": "George",
                                      "document": "000-000-000.00",
                                      "landLordType": "HOTEL",
                                      "email": "test@teste.com.br",
                                      "phone": "+(00) 00 00000-0000",
                                      "active": true
                                    }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land lord not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land Lord not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    LandLord findById(
            @PathVariable("id") long id
    );

    @Operation(
            summary = "Update land lord.",
            description = "Update land lord by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Land lord Updated with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                value = """
                                    {
                                      "id": 1,
                                      "name": "George",
                                      "document": "000-000-000.00",
                                      "landLordType": "HOTEL",
                                      "email": "test@teste.com.br",
                                      "phone": "+(00) 00 00000-0000",
                                      "active": true
                                    }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Land lord already exists with email.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land lord already exists with email.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land lord not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land lord not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    LandLord update(
            @PathVariable("id") long id, @Valid @RequestBody UpdateRequestLandLordDto updateRequestLandLordDto
    );

    @Operation(
            summary = "Update land lord.",
            description = "Update land lord by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "409",
                    description = "Land lord are already inactivated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land lord are already inactivated.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land lord not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LandLord.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land lord not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void inactivate(
            @PathVariable("id") long id
    );
}
