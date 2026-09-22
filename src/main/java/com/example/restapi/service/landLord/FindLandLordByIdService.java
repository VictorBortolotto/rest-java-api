package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindLandLordByIdService {

    private final LandLordRepository landLordRepository;

    public LandLord findById(long id) {
        return landLordRepository.findById(id).orElseThrow(() -> new NotFoundException("Land Lord not found"));
    }
}
