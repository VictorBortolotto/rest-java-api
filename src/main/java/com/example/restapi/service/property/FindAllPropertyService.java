package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.service.landLord.FindLandLordByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllPropertyService {

    private final PropertyRepository propertyRepository;
    private FindLandLordByIdService findLandLordByIdService;

    public List<Property> findAll(Long id, Long landLordId, Boolean isAvaliable, Boolean isActive) {
        if (landLordId != null) findLandLordByIdService.findById(landLordId);

        List<Property> properties = propertyRepository.findAll(id, landLordId, isAvaliable, isActive);

        if (properties.isEmpty()) {
            throw new NotFoundException("Properties not found");
        }

        return properties;
    }
}
