package com.example.restapi.controller.landLord;

import com.example.restapi.controller.docs.landLord.LandLordControllerDocs;
import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;
import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.service.landLord.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/landlord")
public class LandLordController implements LandLordControllerDocs {

    private final CreateLandLordService createLandLordService;
    private final FindAllLandLordService findAllLandLordService;
    private final FindLandLordByIdService findLandLordByIdService;
    private final UpdateLandLordService updateLandLordService;
    private final ReactivateLandLordService reactivateLandLordService;
    private final InactivateLandLordService inactivateLandLordService;

    @Override
    public LandLord create(CreateRequestLandLordDto createRequestLandLordDto) {
        return createLandLordService.create(createRequestLandLordDto);
    }

    @Override
    public List<LandLord> findAll() {
        return findAllLandLordService.findAll();
    }

    @Override
    public LandLord findById(long id) {
        return findLandLordByIdService.findById(id);
    }

    @Override
    public LandLord update(long id, UpdateRequestLandLordDto updateRequestLandLordDto) {
        return updateLandLordService.update(id, updateRequestLandLordDto);
    }

    @Override
    public void inactivate(long id) {
        inactivateLandLordService.inactivate(id);
    }

    @Override
    public void reactivate(long id) {reactivateLandLordService.reactivate(id);}

}
