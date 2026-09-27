package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReactivateCostumerService {

    private final CostumerRepository costumerRepository;
    private final FindCostumerByIdService findCostumerByIdService;

    public void reactivate(long id) {
        Costumer costumer = findCostumerByIdService.findById(id);

        if (costumer.isActive()) {
            throw new ConflictException("Costumer are already activated.");
        }

        costumer.setActive(true);

        costumerRepository.save(costumer);
    }
}
