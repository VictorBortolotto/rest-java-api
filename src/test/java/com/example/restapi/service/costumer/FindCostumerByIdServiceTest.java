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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindCostumerByIdServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private FindCostumerByIdService findCostumerByIdService;

    @Test
    void shouldReturnCostumerSuccessfully() {

        Costumer costumer = CostumerMock.costumerMock();

        when(costumerRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(costumer));

        Costumer result = findCostumerByIdService.findById(1L);

        assertNotNull(result);

        verify(costumerRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDocumentAlreadyExists() {
        when(costumerRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> findCostumerByIdService.findById(1L)
        );
    }
}
