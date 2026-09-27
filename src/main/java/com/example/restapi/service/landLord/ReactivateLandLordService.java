package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactivateLandLordService {

    private final LandLordRepository landLordRepository;
    private final FindLandLordByIdService findLandLordByIdService;

    public void reactivate(long id) {
        LandLord landLord = findLandLordByIdService.findById(id);

        if (landLord.isActive()) {
            throw new ConflictException("Property are already activated.");
        }

        landLord.setActive(true);

        landLordRepository.save(landLord);
    }
}
