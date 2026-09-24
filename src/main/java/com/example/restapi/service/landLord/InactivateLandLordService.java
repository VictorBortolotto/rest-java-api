package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.service.property.FindAllPropertyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InactivateLandLordService {

    private final LandLordRepository landLordRepository;
    private final PropertyRepository propertyRepository;
    private final FindLandLordByIdService findLandLordByIdService;
    private final FindAllPropertyService findAllPropertyService;

    public void inactivate(long id) {
        LandLord landLord = findLandLordByIdService.findById(id);

        if (!landLord.isActive()) {
            throw new ConflictException("Landlord are already inactivated");
        }

        inactivateAllActivePropertiesByLandLordId(landLord.getId());

        landLord.setActive(false);

        landLordRepository.save(landLord);
    }

    private void inactivateAllActivePropertiesByLandLordId(long idLandLord) {
        List<Property> properties = findAllPropertyService.findAll(null, idLandLord, null, true);

        if (properties.isEmpty()) {
            return;
        }

        List<Property> unactivatedProperties = new ArrayList<>();

        for (Property property : properties) {
            property.setActive(false);
            unactivatedProperties.add(property);
        }

        propertyRepository.saveAll(unactivatedProperties);
    }

}
