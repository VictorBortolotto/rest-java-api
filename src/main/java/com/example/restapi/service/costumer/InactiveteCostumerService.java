package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InactiveteCostumerService {

    private final CostumerRepository costumerRepository;
    private final FindCostumerByIdService findCostumerByIdService;

    public void inactivate(long id) {
        Costumer costumer = findCostumerByIdService.findById(id);

        if (!costumer.isActive()) {
            throw new ConflictException("Costumer are already inactivated");
        }

        costumer.setActive(false);

        costumerRepository.save(costumer);
    }
}
