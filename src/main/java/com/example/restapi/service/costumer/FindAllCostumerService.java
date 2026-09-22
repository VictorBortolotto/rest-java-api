package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllCostumerService {

    private final CostumerRepository costumerRepository;

    public List<Costumer> findAll(Boolean isActive) {
        List<Costumer> costumers = costumerRepository.findAll(isActive);

        if (costumers.isEmpty()) {
            throw new NotFoundException("Costumers not found.");
        }

        return costumers;
    }
}
