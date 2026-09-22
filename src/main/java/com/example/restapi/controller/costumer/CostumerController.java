package com.example.restapi.controller.costumer;

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
public class CostumerController {

    private final CreateCostumerService createCostumerService;
    private final FindAllCostumerService findAllCostumerService;
    private final FindCostumerByIdService findCostumerByIdService;
    private final UpdateCostumerService updateCostumerService;
    private final InactiveteCostumerService inactiveteCostumerService;

    @PostMapping
    public Costumer createCostumer(@Valid @RequestBody CreateRequestCostumerDto createRequestCostumerDto) {
        return createCostumerService.create(createRequestCostumerDto);
    }

    @GetMapping
    public List<Costumer> findAll(@RequestParam(name = "is_active", required = false) Boolean isActive) {
        return findAllCostumerService.findAll(isActive);
    }

    @GetMapping(path = "/{id}")
    public Costumer findAll(@PathVariable("id") long id) {
        return findCostumerByIdService.findById(id);
    }

    @PutMapping(path = "/{id}")
    public Costumer update(@PathVariable("id") long id, @RequestBody UpdateRequestCostumerDto updateRequestCostumerDto) {
        return updateCostumerService.update(id, updateRequestCostumerDto);
    }

    @PatchMapping(path = "/{id}")
    public void inactivate(@PathVariable("id") long id) {
        inactiveteCostumerService.inactivate(id);
    }
}
