package com.example.restapi.service.landLord;

import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;
import com.example.restapi.domain.enums.LandLordType;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateLandLordService {

    private LandLordRepository landLordRepository;

    public LandLord create(CreateRequestLandLordDto createRequestLandLordDto) {
        boolean isLandLordAlreadyExistsByDocument = landLordRepository.findByDocument(createRequestLandLordDto.document()).isEmpty();
        boolean isLandLordAlreadyExistsByEmail = landLordRepository.findByEmail(createRequestLandLordDto.email()).isEmpty();

        if (!isLandLordAlreadyExistsByDocument || !isLandLordAlreadyExistsByEmail) {
            throw new ConflictException("Land Lord already exists with email or document.");
        }

        LandLord landLord = new LandLord();

        landLord.setActive(true);
        landLord.setDocument(createRequestLandLordDto.document());
        landLord.setEmail(createRequestLandLordDto.email());
        landLord.setPhone(createRequestLandLordDto.phone());
        landLord.setName(createRequestLandLordDto.name());
        landLord.setLandLordType(LandLordType.valueOf(createRequestLandLordDto.type().toUpperCase()));

        return landLordRepository.save(landLord);
    }
}
