package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InactivatePropertyService {

    private final PropertyRepository propertyRepository;
    private final FindPropertyByIdService findPropertyByIdService;

    public void inactivate(long id) {
        Property property = findPropertyByIdService.findById(id);

        if (!property.isActive()) {
            throw new ConflictException("Property are already inactivated");
        }

        property.setActive(false);

        propertyRepository.save(property);
    }
}
