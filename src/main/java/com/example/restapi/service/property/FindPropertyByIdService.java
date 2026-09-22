package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPropertyByIdService {

    private final PropertyRepository propertyRepository;

    public Property findById(long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new NotFoundException("Property not found."));
    }

}
