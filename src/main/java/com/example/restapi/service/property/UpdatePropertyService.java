package com.example.restapi.service.property;

import com.example.restapi.domain.dto.property.UpdatePropertyDto;
import com.example.restapi.domain.enums.PropertyType;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.exceptions.InsufficentValueExcpetion;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.service.landLord.FindLandLordByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePropertyService {

    private final PropertyRepository propertyRepository;
    private final FindPropertyByIdService findPropertyByIdService;
    private final FindLandLordByIdService findLandLordByIdService;

    public Property update(long id, UpdatePropertyDto updatePropertyDto) {
        Property property = findPropertyByIdService.findById(id);

        LandLord landLord = findLandLordByIdService.findById(property.getLandLord().getId());

        if (!landLord.isActive()) {
            throw new InactivatedException("Inactive Landlord.");
        }

        if (updatePropertyDto.capacity() <= 0) {
            throw new InsufficentValueExcpetion("Capacity must be greater than zero.");
        }

        if (updatePropertyDto.dailyRate() <= 0) {
            throw new InsufficentValueExcpetion("Daily rate must be greater than zero.");
        }

        property.setName(updatePropertyDto.name());
        property.setPropertyType(PropertyType.valueOf(updatePropertyDto.propertyType().toUpperCase()));
        property.setAddress(updatePropertyDto.address());
        property.setNumber(updatePropertyDto.number());
        property.setNeighborhood(updatePropertyDto.neighborhood());
        property.setCity(updatePropertyDto.city());
        property.setState(updatePropertyDto.state());
        property.setZipCode(updatePropertyDto.zipCode());
        property.setCapacity(updatePropertyDto.capacity());
        property.setDailyRate(updatePropertyDto.dailyRate());
        property.setNotes(updatePropertyDto.notes());

        return propertyRepository.save(property);
    }
}
