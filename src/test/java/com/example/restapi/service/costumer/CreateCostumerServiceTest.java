package com.example.restapi.service.costumer;

import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import com.example.restapi.mock.dto.costumer.CreateCostumerDtoMock;
import com.example.restapi.mock.entity.CostumerMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCostumerServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private CreateCostumerService createCostumerService;

    @Test
    void shouldCreateCostumerSuccessfully() {

        CreateRequestCostumerDto dto = CreateCostumerDtoMock.createRequestCostumerDtoMock();
        Costumer costumer = CostumerMock.costumerMock();

        when(costumerRepository.findByDocument(dto.document()))
                .thenReturn(Optional.empty());

        when(costumerRepository.findByEmail(dto.email()))
                .thenReturn(Optional.empty());

        when(costumerRepository.save(any(Costumer.class)))
                .thenReturn(costumer);

        Costumer result = createCostumerService.create(dto);

        assertNotNull(result);
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.document(), result.getDocument());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.phone(), result.getPhone());
        assertTrue(result.isActive());

        verify(costumerRepository, times(1)).findByDocument(dto.document());
        verify(costumerRepository, times(1)).findByEmail(dto.email());
        verify(costumerRepository, times(1)).save(any(Costumer.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenDocumentAlreadyExists() {

        CreateRequestCostumerDto dto = CreateCostumerDtoMock.createRequestCostumerDtoMock();
        Costumer costumer = CostumerMock.costumerMock();

        when(costumerRepository.findByDocument(dto.document()))
                .thenReturn(Optional.of(costumer));

        when(costumerRepository.findByEmail(dto.email()))
                .thenReturn(Optional.empty());

        assertThrows(
                ConflictException.class,
                () -> createCostumerService.create(dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenEmailAlreadyExists() {

        CreateRequestCostumerDto dto = CreateCostumerDtoMock.createRequestCostumerDtoMock();
        Costumer costumer = CostumerMock.costumerMock();

        when(costumerRepository.findByDocument(dto.document()))
                .thenReturn(Optional.empty());

        when(costumerRepository.findByEmail(dto.email()))
                .thenReturn(Optional.of(costumer));

        assertThrows(
                ConflictException.class,
                () -> createCostumerService.create(dto)
        );
    }
}
