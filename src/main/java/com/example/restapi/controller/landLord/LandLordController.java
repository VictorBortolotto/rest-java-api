package com.example.restapi.controller.landLord;

import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;
import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.service.landLord.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/landlord")
public class LandLordController {

    private final CreateLandLordService createLandLordService;
    private final FindAllLandLordService findAllLandLordService;
    private final FindLandLordByIdService findLandLordByIdService;
    private final UpdateLandLordService updateLandLordService;
    private final InactivateLandLordService inactivateLandLordService;

    @PostMapping
    public LandLord create(@Valid @RequestBody CreateRequestLandLordDto createRequestLandLordDto) {
        return createLandLordService.create(createRequestLandLordDto);
    }

    @GetMapping
    public List<LandLord> findAll() {
        return findAllLandLordService.findAll();
    }

    @GetMapping(path = "/{id}")
    public LandLord findById(@PathVariable("id") long id) {
        return findLandLordByIdService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public LandLord update(@PathVariable("id") long id, @Valid @RequestBody UpdateRequestLandLordDto updateRequestLandLordDto) {
        return updateLandLordService.update(id, updateRequestLandLordDto);
    }

    @PatchMapping(path = "/{id}")
    public void inactivate(@PathVariable("id") long id) {
        inactivateLandLordService.inactivate(id);
    }

}
