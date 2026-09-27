package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactivatePropertyService {

    private final PropertyRepository propertyRepository;
    private final FindPropertyByIdService propertyByIdService;

    public void reactivate(long id) {
        Property property = propertyByIdService.findById(id);

        if (property.isActive()) {
            throw new ConflictException("Property are already activated.");
        }

        if (!property.getLandLord().isActive()) {
            throw new InactivatedException("Inactive land lord.");
        }

        property.setActive(true);

        propertyRepository.save(property);
    }
}
