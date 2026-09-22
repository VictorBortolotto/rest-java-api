package com.example.restapi.service.costumer;

import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCostumerService {

    private final CostumerRepository costumerRepository;

    public Costumer create(CreateRequestCostumerDto createRequestCostumerDto) {
        boolean isLandLordAlreadyExistsByDocument = costumerRepository.findByDocument(createRequestCostumerDto.document()).isEmpty();
        boolean isLandLordAlreadyExistsByEmail = costumerRepository.findByEmail(createRequestCostumerDto.email()).isEmpty();

        if (!isLandLordAlreadyExistsByDocument || !isLandLordAlreadyExistsByEmail) {
            throw new ConflictException("Costumer already exists with email or document.");
        }

        Costumer costumer = new Costumer();

        costumer.setEmail(createRequestCostumerDto.email());
        costumer.setDocument(createRequestCostumerDto.document());
        costumer.setName(createRequestCostumerDto.name());
        costumer.setPhone(createRequestCostumerDto.phone());
        costumer.setActive(true);

        return costumerRepository.save(costumer);
    }
}
