package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import com.example.restapi.mock.entity.CostumerMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllCostumerServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private FindAllCostumerService findAllCostumerService;

    @Test
    void shouldReturnCostumerListSuccessfully() {

        Costumer costumer = CostumerMock.costumerMock();

        when(costumerRepository.findAll(any(Boolean.class)))
                .thenReturn(List.of(costumer));

        List<Costumer> results = findAllCostumerService.findAll(true);

        assertNotNull(results);
        assertFalse(results.isEmpty());

        verify(costumerRepository, times(1)).findAll(true);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCostumerNotExists() {
        when(costumerRepository.findAll(any(Boolean.class)))
                .thenReturn(List.of());

        assertThrows(
                NotFoundException.class,
                () -> findAllCostumerService.findAll(true)
        );
    }
}
