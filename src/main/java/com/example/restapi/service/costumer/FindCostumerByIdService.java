package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindCostumerByIdService {

    private final CostumerRepository costumerRepository;

    public Costumer findById(long id) {
        return costumerRepository.findById(id).orElseThrow(() -> new NotFoundException("Costumer not found"));
    }
}
