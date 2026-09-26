package com.example.restapi.service.landLord;

import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateLandLordService {

    private final LandLordRepository landLordRepository;
    private final FindLandLordByIdService findLandLordByIdService;

    public LandLord update(long id, UpdateRequestLandLordDto updateRequestLandLordDto) {
        LandLord landLord = findLandLordByIdService.findById(id);

        if (!landLord.isActive()) {
            throw new InactivatedException("Inactivated land lord");
        }

        boolean isLandLordAlreadyExistsByEmail = landLordRepository.findByEmailAndIdNot(updateRequestLandLordDto.email(), id).isEmpty();

        if (!isLandLordAlreadyExistsByEmail) {
            throw new ConflictException("Land Lord already exists with email.");
        }

        landLord.setId(id);
        landLord.setEmail(updateRequestLandLordDto.email());
        landLord.setPhone(updateRequestLandLordDto.phone());
        landLord.setName(updateRequestLandLordDto.name());

        return landLordRepository.save(landLord);
    }
}
