package com.example.restapi.controller.docs.reservation;

import com.example.restapi.domain.dto.reservation.CreateReservationDto;
import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Reservation;
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

public interface ReservationControllerDocs {

    @Operation(
            summary = "Create reserve.",
            description = "Create a new reserve."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Reserve created with success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "id": 1,
                                          "costumer": {
                                            "id": 1,
                                            "name": "Teste",
                                            "document": "1234556737",
                                            "email": "teste3@teste.com",
                                            "phone": "123456",
                                            "active": true
                                          },
                                          "property": {
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
                                          },
                                          "checkInDate": "2026-09-01",
                                          "checkOutDate": "2026-09-20",
                                          "numberOfCostumers": 2,
                                          "totalAmount": 76,
                                          "status": "ACTIVE"
                                        }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "410",
                    description = "Inactivated costumer.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Inactivated costumer.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "410",
                    description = "Inactivated property.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Inactivated property.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Reservation conflicts with other reservations for the same period.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Reservation conflicts with other reservations for the same period.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "The check-out date must be later than the check-in date.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "The check-out date must be later than the check-in date.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "The number of guests must be greater than zero.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "The number of guests must be greater than zero.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "The number of guests cannot exceed the property's capacity.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "The number of guests cannot exceed the property's capacity.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Reservation create(
            @Valid @RequestBody CreateReservationDto createReservationDto
    );

    @Operation(
            summary = "Find all reservations.",
            description = "Find all reservations."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reservations successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                [
                                        {
                                          "id": 1,
                                          "costumer": {
                                            "id": 1,
                                            "name": "Teste",
                                            "document": "1234556737",
                                            "email": "teste3@teste.com",
                                            "phone": "123456",
                                            "active": true
                                          },
                                          "property": {
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
                                          },
                                          "checkInDate": "2026-09-01",
                                          "checkOutDate": "2026-09-20",
                                          "numberOfCostumers": 2,
                                          "totalAmount": 76,
                                          "status": "ACTIVE"
                                        }
                                ]
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reservations not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Reservations not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Reservation> findAll(
            @RequestParam(required = false) Long id,
            @RequestParam(name = "client_id", required = false) Long clientId,
            @RequestParam(name = "property_id", required = false) Long propertyId,
            @RequestParam(name = "status", required = false) ReservationStatus status
    );

    @Operation(
            summary = "Find reserve by ID.",
            description = "Find reserve by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reserve successfully returned.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                        {
                                          "id": 1,
                                          "costumer": {
                                            "id": 1,
                                            "name": "Teste",
                                            "document": "1234556737",
                                            "email": "teste3@teste.com",
                                            "phone": "123456",
                                            "active": true
                                          },
                                          "property": {
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
                                          },
                                          "checkInDate": "2026-09-01",
                                          "checkOutDate": "2026-09-20",
                                          "numberOfCostumers": 2,
                                          "totalAmount": 76,
                                          "status": "ACTIVE"
                                        }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserve not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Reserve not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Reservation findById(
            @PathVariable("id") long id
    );

    @Operation(
            summary = "Cancel reserve.",
            description = "Cancel reserve by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "409",
                    description = "The reservation has already been cancelled or finalized.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "The reservation has already been cancelled or finalized.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserve not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Reserve not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PatchMapping(path = "/cancel/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void cancel(
            @PathVariable(value = "id", required = true) long id
    );

    @Operation(
            summary = "Complete reserve.",
            description = "Complete reserve by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "409",
                    description = "To finalize the reservation, it needs to be active.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "To finalize the reservation, it needs to be active.",
                                    "errors": null
                                }
                                """
                            )

                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Reserve not found.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "message": "Reserve not found.",
                                    "errors": null
                                }
                                """
                            )

                    )
            )
    })
    @PatchMapping(path = "/complete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void complete(
            @PathVariable(value = "id", required = true) long id
    );
}
