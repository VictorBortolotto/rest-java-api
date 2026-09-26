package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.mock.entity.LandLordMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllLandLordServiceTest {

    @Mock
    private LandLordRepository landLordRepository;

    @InjectMocks
    private FindAllLandLordService findAllLandLordService;

    @Test
    void shouldReturnLandLordListSuccessfully() {

        LandLord landLord = LandLordMock.landLordMock();

        when(landLordRepository.findAll())
                .thenReturn(List.of(landLord));

        List<LandLord> results = findAllLandLordService.findAll();

        assertNotNull(results);
        assertFalse(results.isEmpty());

        verify(landLordRepository, times(1)).findAll();
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCostumerNotExists() {
        when(landLordRepository.findAll())
                .thenReturn(List.of());

        assertThrows(
                NotFoundException.class,
                () -> findAllLandLordService.findAll()
        );
    }
}
