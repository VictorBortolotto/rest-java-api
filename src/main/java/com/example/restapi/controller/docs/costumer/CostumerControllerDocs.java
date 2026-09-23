package com.example.restapi.controller.docs.costumer;

import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;
import com.example.restapi.domain.dto.costumer.UpdateRequestCostumerDto;
import com.example.restapi.domain.model.Costumer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CostumerControllerDocs {

    @Operation(
            summary = "Create costumer.",
            description = "Create a new costumer."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Costumer created with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "id": 1,
                                    "name": "Dalle",
                                    "document": "000-000-000.00",
                                    "email": "test@test.com.br",
                                    "phone": "+(00) 00 00000-0000",
                                    "active": true
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Costumer already exists with email or document",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumer already exists with email or document.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Costumer create(
            @RequestBody CreateRequestCostumerDto dto
    );

    @Operation(
            summary = "Find all costumers.",
            description = "Find all costumers."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customers successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                [
                                    {
                                        "id": 1,
                                        "name": "Dalle",
                                        "document": "000-000-000.00",
                                        "email": "test@test.com.br",
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
                    description = "Costumers not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumers not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Costumer> findAll(
            @RequestParam(name = "is_active", required = false) Boolean isActive
    );

    @Operation(
            summary = "Find costumer by ID.",
            description = "Find costumer by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "id": 1,
                                    "name": "Dalle",
                                    "document": "000-000-000.00",
                                    "email": "test@test.com.br",
                                    "phone": "+(00) 00 00000-0000",
                                    "active": true
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Costumers not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumer not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Costumer findById(
            @PathVariable("id") long id
    );

    @Operation(
            summary = "Update costumer.",
            description = "Update costumer by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Costumer Updated with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "id": 1,
                                    "name": "Dalle",
                                    "document": "000-000-000.00",
                                    "email": "test@test.com.br",
                                    "phone": "+(00) 00 00000-0000",
                                    "active": true
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Costumer already exists with email.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumer already exists with email.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Costumers not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumer not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Costumer update(
            @PathVariable("id") long id, @RequestBody UpdateRequestCostumerDto updateRequestCostumerDto
    );

    @Operation(
            summary = "Update costumer.",
            description = "Update costumer by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "409",
                    description = "Costumer are already inactivated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumer are already inactivated.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Costumers not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Costumer.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Costumer not found.",
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
