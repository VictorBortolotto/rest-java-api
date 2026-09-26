package com.example.restapi.service.costumer;

import com.example.restapi.domain.dto.costumer.UpdateRequestCostumerDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.repository.CostumerRepository;
import com.example.restapi.mock.dto.costumer.UpdateCostumerDtoMock;
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
public class UpdateCostumerServiceTest {

    @Mock
    private CostumerRepository costumerRepository;

    @Mock
    private FindCostumerByIdService findCostumerByIdService;

    @InjectMocks
    private UpdateCostumerService updateCostumerService;

    @Test
    void shouldUpdateCostumerSuccessfully() {

        UpdateRequestCostumerDto dto = UpdateCostumerDtoMock.updateRequestCostumerDtoMock();
        Costumer costumer = CostumerMock.costumerMock();
        costumer.setName("George 1");
        costumer.setEmail("george1@gmail.com");
        costumer.setPhone("+(55) 48 99986-0909");

        when(costumerRepository.findByEmailAndIdNot(any(String.class), any(Long.class)))
                .thenReturn(Optional.empty());

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(costumerRepository.save(any(Costumer.class)))
                .thenReturn(costumer);

        Costumer result = updateCostumerService.update(1L, dto);

        assertNotNull(result);
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.phone(), result.getPhone());
        assertTrue(result.isActive());

        verify(costumerRepository, times(1)).findByEmailAndIdNot(any(String.class), any(Long.class));
        verify(findCostumerByIdService, times(1)).findById(any(Long.class));
        verify(costumerRepository, times(1)).save(any(Costumer.class));
    }

    @Test
    void shouldThrowInactivatedExceptionWhenCostumerAreInactive() {

        UpdateRequestCostumerDto dto = UpdateCostumerDtoMock.updateRequestCostumerDtoMock();
        Costumer costumer = CostumerMock.costumerMock();
        costumer.setActive(false);

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        assertThrows(
                InactivatedException.class,
                () -> updateCostumerService.update(1L, dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenCostumerAlreadyExistsWithEmail() {

        UpdateRequestCostumerDto dto = UpdateCostumerDtoMock.updateRequestCostumerDtoMock();
        Costumer costumer = CostumerMock.costumerMock();

        Costumer costumer2 = CostumerMock.costumerMock();
        costumer2.setId(2L);

        when(findCostumerByIdService.findById(any(Long.class)))
                .thenReturn(costumer);

        when(costumerRepository.findByEmailAndIdNot(any(String.class), any(Long.class)))
                .thenReturn(Optional.of(costumer2));

        assertThrows(
                ConflictException.class,
                () -> updateCostumerService.update(1L, dto)
        );
    }

}
