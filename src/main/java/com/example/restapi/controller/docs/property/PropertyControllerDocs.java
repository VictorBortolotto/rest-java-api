package com.example.restapi.controller.docs.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;
import com.example.restapi.domain.dto.property.UpdatePropertyDto;
import com.example.restapi.domain.model.Property;
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

public interface PropertyControllerDocs {
    @Operation(
            summary = "Create Property.",
            description = "Create a new Property."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Property created with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "id": 1,
                                          "landLord": {
                                            "id": 1,
                                            "name": "George",
                                            "document": "000-000-000.00",
                                            "landLordType": "HOTEL",
                                            "email": "test@teste.com.br",
                                            "phone": "+(00) 00 00000-0000",
                                            "active": true
                                          },
                                          "name": "National Res.",
                                          "propertyType": "ROOM",
                                          "address": "1234 Main Street",
                                          "number": "1234",
                                          "neighborhood": "Center",
                                          "city": "Springfield",
                                          "state": "Massachusetts",
                                          "zipCode": "10001",
                                          "capacity": 4,
                                          "dailyRate": 4,
                                          "avaliable": true,
                                          "notes": null,
                                          "active": true
                                        }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land Lord not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land Lord not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "410",
                    description = "Inactive Landlord.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Inactive Landlord.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Capacity must be greater than zero.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Capacity must be greater than zero.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Daily rate must be greater than zero.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Daily rate must be greater than zero.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Property registry(
            @Valid  @RequestBody RegistryPropertyDto registryPropertyDto
    );

    @Operation(
            summary = "Find all properties.",
            description = "Find all properties."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Properties successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                [
                                        {
                                          "id": 1,
                                          "landLord": {
                                            "id": 1,
                                            "name": "George",
                                            "document": "000-000-000.00",
                                            "landLordType": "HOTEL",
                                            "email": "test@teste.com.br",
                                            "phone": "+(00) 00 00000-0000",
                                            "active": true
                                          },
                                          "name": "National Res.",
                                          "propertyType": "ROOM",
                                          "address": "1234 Main Street",
                                          "number": "1234",
                                          "neighborhood": "Center",
                                          "city": "Springfield",
                                          "state": "Massachusetts",
                                          "zipCode": "10001",
                                          "capacity": 4,
                                          "dailyRate": 4,
                                          "avaliable": true,
                                          "notes": null,
                                          "active": true
                                        }
                                ]
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land Lord not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land Lord not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Properties not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Properties not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Property> findAll(
            @RequestParam(required = false) Long id,
            @RequestParam(name = "land_lord_id", required = false) Long landLordId,
            @RequestParam(name = "avaliable", required = false) Boolean isAvaliable,
            @RequestParam(name = "is_active", required = false) Boolean isActive
    );

    @Operation(
            summary = "Find property by ID.",
            description = "Find property by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Property successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                value = """
                                        {
                                          "id": 1,
                                          "landLord": {
                                            "id": 1,
                                            "name": "George",
                                            "document": "000-000-000.00",
                                            "landLordType": "HOTEL",
                                            "email": "test@teste.com.br",
                                            "phone": "+(00) 00 00000-0000",
                                            "active": true
                                          },
                                          "name": "National Res.",
                                          "propertyType": "ROOM",
                                          "address": "1234 Main Street",
                                          "number": "1234",
                                          "neighborhood": "Center",
                                          "city": "Springfield",
                                          "state": "Massachusetts",
                                          "zipCode": "10001",
                                          "capacity": 4,
                                          "dailyRate": 4,
                                          "avaliable": true,
                                          "notes": null,
                                          "active": true
                                        }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Property not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Property not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Property findById(
            @PathVariable("id") long id
    );

    @Operation(
            summary = "Update property.",
            description = "Update property by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Property updated with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                value = """
                                        {
                                          "id": 1,
                                          "landLord": {
                                            "id": 1,
                                            "name": "George",
                                            "document": "000-000-000.00",
                                            "landLordType": "HOTEL",
                                            "email": "test@teste.com.br",
                                            "phone": "+(00) 00 00000-0000",
                                            "active": true
                                          },
                                          "name": "National Res.",
                                          "propertyType": "ROOM",
                                          "address": "1234 Main Street",
                                          "number": "1234",
                                          "neighborhood": "Center",
                                          "city": "Springfield",
                                          "state": "Massachusetts",
                                          "zipCode": "10001",
                                          "capacity": 4,
                                          "dailyRate": 4,
                                          "avaliable": true,
                                          "notes": null,
                                          "active": true
                                        }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Property not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Property not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Land Lord not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Land Lord not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "410",
                    description = "Inactive Landlord.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Inactive Landlord.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Capacity must be greater than zero.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Capacity must be greater than zero.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Daily rate must be greater than zero.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Daily rate must be greater than zero.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Property update(
            @PathVariable("id") long id, @Valid @RequestBody UpdatePropertyDto updatePropertyDto
    );

    @Operation(
            summary = "Inactivate property.",
            description = "Inactivate property by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Property inactivated successfully."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Property are already inactivated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Property are already inactivated.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Property not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Property.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Property not found.",
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
