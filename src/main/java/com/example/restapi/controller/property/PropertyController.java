package com.example.restapi.controller.property;

import com.example.restapi.controller.docs.property.PropertyControllerDocs;
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
public class PropertyController implements PropertyControllerDocs {

    private final RegistryPropertyService registryPropertyService;
    private final FindAllPropertyService findAllPropertyService;
    private final FindPropertyByIdService findPropertyByIdService;
    private final InactivatePropertyService inactivatePropertyService;
    private final UpdatePropertyService updatePropertyService;

    @Override
    public Property registry(RegistryPropertyDto registryPropertyDto) {
        return registryPropertyService.registry(registryPropertyDto);
    }

    @Override
    public List<Property> findAll(Long id,  Long landLordId, Boolean isAvaliable, Boolean isActive
    ) {
        return findAllPropertyService.findAll(id, landLordId, isAvaliable, isActive);
    }

    @Override
    public Property findById(long id) {
        return findPropertyByIdService.findById(id);
    }

    @Override
    public void inactivate(long id) {
        inactivatePropertyService.inactivate(id);
    }

    @Override
    public Property update(long id, UpdatePropertyDto updatePropertyDto) {
        return updatePropertyService.update(id, updatePropertyDto);
    }
}