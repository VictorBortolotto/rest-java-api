package com.example.restapi.controller.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;
import com.example.restapi.domain.dto.property.UpdatePropertyDto;
import com.example.restapi.domain.model.Property;
import com.example.restapi.service.property.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/property")
public class PropertyController {

    private final RegistryPropertyService registryPropertyService;
    private final FindAllPropertyService findAllPropertyService;
    private final FindPropertyByIdService findPropertyByIdService;
    private final InactivatePropertyService inactivatePropertyService;
    private final UpdatePropertyService updatePropertyService;

    @PostMapping
    public Property registry(@Valid  @RequestBody RegistryPropertyDto registryPropertyDto) {
        return registryPropertyService.registryProperty(registryPropertyDto);
    }

    @GetMapping
    public List<Property> findAll(
            @RequestParam(required = false) Long id,
            @RequestParam(name = "land_lord_id", required = false) Long landLordId,
            @RequestParam(name = "avaliable", required = false) Boolean isAvaliable,
            @RequestParam(name = "is_active", required = false) Boolean isActive
    ) {
        return findAllPropertyService.findAll(id, landLordId, isAvaliable, isActive);
    }

    @GetMapping(path = "/{id}")
    public Property findById(@PathVariable("id") long id) {
        return findPropertyByIdService.findById(id);
    }

    @PatchMapping(path = "/{id}")
    public void inactivate(@PathVariable("id") long id) {
        inactivatePropertyService.inactivate(id);
    }

    @PutMapping(path = "/{id}")
    public Property update(@PathVariable("id") long id, @Valid  @RequestBody UpdatePropertyDto updatePropertyDto) {
        return updatePropertyService.update(id, updatePropertyDto);
    }
}