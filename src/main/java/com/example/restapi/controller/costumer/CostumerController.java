package com.example.restapi.controller.costumer;

import com.example.restapi.controller.docs.costumer.CostumerControllerDocs;
import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;
import com.example.restapi.domain.dto.costumer.UpdateRequestCostumerDto;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.service.costumer.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/costumer")
public class CostumerController implements CostumerControllerDocs {

    private final CreateCostumerService createCostumerService;
    private final FindAllCostumerService findAllCostumerService;
    private final FindCostumerByIdService findCostumerByIdService;
    private final UpdateCostumerService updateCostumerService;
    private final InactiveteCostumerService inactiveteCostumerService;

    @Override
    public Costumer create(CreateRequestCostumerDto createRequestCostumerDto) {
        return createCostumerService.create(createRequestCostumerDto);
    }

    @Override
    public List<Costumer> findAll(Boolean isActive) {
        return findAllCostumerService.findAll(isActive);
    }

    @Override
    public Costumer findById(long id) {
        return findCostumerByIdService.findById(id);
    }

    @Override
    public Costumer update(long id, UpdateRequestCostumerDto updateRequestCostumerDto) {
        return updateCostumerService.update(id, updateRequestCostumerDto);
    }

    @Override
    public void inactivate(long id) {
        inactiveteCostumerService.inactivate(id);
    }
}
