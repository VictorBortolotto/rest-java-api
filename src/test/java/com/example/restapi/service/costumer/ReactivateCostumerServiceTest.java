package com.example.restapi.service.costumer;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import com.example.restapi.mock.entity.CostumerMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReactivateCostumerServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @Mock
    private FindCostumerByIdService findCostumerByIdService;

    @InjectMocks
    private ReactivateCostumerService reactivateCostumerService;

    @Test
    void shouldReactivateCostumerSuccessfully() {
        Costumer costumer = CostumerMock.costumerMock();
        costumer.setActive(false);

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(costumerRepository.save(any(Costumer.class)))
                .thenReturn(costumer);

        reactivateCostumerService.reactivate(1L);

        verify(findCostumerByIdService, times(1)).findById(any(Long.class));
        verify(costumerRepository, times(1)).save(any(Costumer.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenCostumerAreActive() {

        Costumer costumer = CostumerMock.costumerMock();

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        assertThrows(
                ConflictException.class,
                () -> reactivateCostumerService.reactivate(1L)
        );
    }
}
