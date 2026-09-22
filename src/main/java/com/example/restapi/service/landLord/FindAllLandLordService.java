package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindAllLandLordService {

    private final LandLordRepository landLordRepository;

    public List<LandLord> findAll() {
        List<LandLord> landLords = landLordRepository.findAll();

        if (landLords.isEmpty()) {
            throw new NotFoundException("Land Lords not found.");
        }

        return landLords;
    }
}
