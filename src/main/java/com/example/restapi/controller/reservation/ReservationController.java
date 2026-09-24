package com.example.restapi.controller.reservation;

import com.example.restapi.controller.docs.reservation.ReservationControllerDocs;
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
public class ReservationController implements ReservationControllerDocs {

    private final CreateReservationService createReservationService;
    private final FindReservationByIdService findReservationByIdService;
    private final FindAllReservationService findAllReservationService;
    private final CompleteReservationService completeReservationService;
    private final CancelReservationService cancelReservationService;

    @Override
    public Reservation create(CreateReservationDto createReservationDto) {
        return createReservationService.create(createReservationDto);
    }

    @Override
    public Reservation findById(long id) {
        return findReservationByIdService.findById(id);
    }

    @Override
    public List<Reservation> findAll(Long id, Long clientId, Long propertyId, ReservationStatus status
    ) {
        return findAllReservationService.findAll(clientId, propertyId, status);
    }

    @Override
    public void cancel(long id) {
        cancelReservationService.cancel(id);
    }

    @Override
    public void complete(long id) {
        completeReservationService.complete(id);
    }
}
