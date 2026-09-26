package com.example.restapi.service.costumer;

import com.example.restapi.domain.dto.costumer.UpdateRequestCostumerDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCostumerService {

    private final CostumerRepository costumerRepository;
    private final FindCostumerByIdService findCostumerByIdService;

    public Costumer update(long id, UpdateRequestCostumerDto updateRequestCostumerDto) {
        Costumer costumer = findCostumerByIdService.findById(id);

        if (!costumer.isActive()) {
            throw new InactivatedException("Inactivated costumer");
        }

        boolean isCostumerAlreadyExistsByEmail = costumerRepository.findByEmailAndIdNot(updateRequestCostumerDto.email(), id).isPresent();

        if (isCostumerAlreadyExistsByEmail) {
            throw new ConflictException("Costumer already exists with email.");
        }

        costumer.setPhone(updateRequestCostumerDto.phone());
        costumer.setName(updateRequestCostumerDto.name());
        costumer.setEmail(updateRequestCostumerDto.email());

        return costumerRepository.save(costumer);
    }
}
