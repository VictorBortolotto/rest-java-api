package com.example.restapi.service.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;
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
public class RegistryPropertyService {

    private final PropertyRepository propertyRepository;
    private final FindLandLordByIdService findLandLordByIdService;

    public Property registryProperty(RegistryPropertyDto registryPropertyDto) {
        LandLord landLord = findLandLordByIdService.findById(registryPropertyDto.landLordId());

        if (!landLord.isActive()) {
            throw new InactivatedException("Inactive Landlord.");
        }

        if (registryPropertyDto.capacity() <= 0) {
            throw new InsufficentValueExcpetion("Capacity must be greater than zero.");
        }

        if (registryPropertyDto.dailyRate() <= 0) {
            throw new InsufficentValueExcpetion("Daily rate must be greater than zero.");
        }

        Property property = new Property();

        property.setLandLord(landLord);
        property.setName(registryPropertyDto.name());
        property.setPropertyType(PropertyType.valueOf(registryPropertyDto.propertyType().toUpperCase()));
        property.setAddress(registryPropertyDto.address());
        property.setNumber(registryPropertyDto.number());
        property.setNeighborhood(registryPropertyDto.neighborhood());
        property.setCity(registryPropertyDto.city());
        property.setState(registryPropertyDto.state());
        property.setZipCode(registryPropertyDto.zipCode());
        property.setCapacity(registryPropertyDto.capacity());
        property.setDailyRate(registryPropertyDto.dailyRate());
        property.setActive(true);
        property.setAvaliable(true);

        return propertyRepository.save(property);
    }
}
