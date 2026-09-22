package com.example.restapi.controller.reservation;

import com.example.restapi.domain.dto.reservation.CreateReservationDto;
import com.example.restapi.domain.enums.ReservationStatus;
import com.example.restapi.domain.model.Reservation;
import com.example.restapi.service.reservation.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    private final CreateReservationService createReservationService;
    private final FindReservationByIdService findReservationByIdService;
    private final FindAllReservationService findAllReservationService;
    private final CompleteReservationService completeReservationService;
    private final CancelReservationService cancelReservationService;

    @PostMapping
    public Reservation create(@Valid @RequestBody CreateReservationDto createReservationDto) {
        return createReservationService.create(createReservationDto);
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable("id") long id) {
        return findReservationByIdService.findById(id);
    }

    @GetMapping
    public List<Reservation> findAll(
            @RequestParam(required = false) Long id,
            @RequestParam(name = "client_id", required = false) Long clientId,
            @RequestParam(name = "property_id", required = false) Long propertyId,
            @RequestParam(name = "status", required = false) ReservationStatus status
    ) {
        return findAllReservationService.findAll(clientId, propertyId, status);
    }

    @PatchMapping(path = "/cancel/{id}")
    public void cancel(@PathVariable(value = "id", required = true) long id) {
        cancelReservationService.cancel(id);
    }

    @PatchMapping(path = "/complete/{id}")
    public void complete(@PathVariable(value = "id", required = true) long id) {
        completeReservationService.complete(id);
    }
}
