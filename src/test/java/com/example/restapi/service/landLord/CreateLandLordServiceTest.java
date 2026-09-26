package com.example.restapi.service.landLord;

import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;
import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.mock.dto.costumer.CreateCostumerDtoMock;
import com.example.restapi.mock.dto.landLord.CreateRequestLandLordDtoMock;
import com.example.restapi.mock.entity.CostumerMock;
import com.example.restapi.mock.entity.LandLordMock;
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
public class CreateLandLordServiceTest {

    @Mock
    private LandLordRepository landLordRepository;

    @InjectMocks
    private CreateLandLordService createLandLordService;

    @Test
    void shouldCreateLandLordSuccessfully() {

        CreateRequestLandLordDto dto = CreateRequestLandLordDtoMock.createRequestLandLordDtoMock();
        LandLord landLord = LandLordMock.landLordMock();

        when(landLordRepository.findByDocument(dto.document()))
                .thenReturn(Optional.empty());

        when(landLordRepository.findByEmail(dto.email()))
                .thenReturn(Optional.empty());

        when(landLordRepository.save(any(LandLord.class)))
                .thenReturn(landLord);

        LandLord result = createLandLordService.create(dto);

        assertNotNull(result);
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.document(), result.getDocument());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.phone(), result.getPhone());
        assertTrue(result.isActive());

        verify(landLordRepository, times(1)).findByDocument(dto.document());
        verify(landLordRepository, times(1)).findByEmail(dto.email());
        verify(landLordRepository, times(1)).save(any(LandLord.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenDocumentAlreadyExists() {
        CreateRequestLandLordDto dto = CreateRequestLandLordDtoMock.createRequestLandLordDtoMock();
        LandLord landLord = LandLordMock.landLordMock();

        when(landLordRepository.findByDocument(dto.document()))
                .thenReturn(Optional.of(landLord));

        when(landLordRepository.findByEmail(dto.email()))
                .thenReturn(Optional.empty());

        assertThrows(
                ConflictException.class,
                () -> createLandLordService.create(dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenEmailAlreadyExists() {

        CreateRequestLandLordDto dto = CreateRequestLandLordDtoMock.createRequestLandLordDtoMock();
        LandLord landLord = LandLordMock.landLordMock();

        when(landLordRepository.findByDocument(dto.document()))
                .thenReturn(Optional.empty());

        when(landLordRepository.findByEmail(dto.email()))
                .thenReturn(Optional.of(landLord));

        assertThrows(
                ConflictException.class,
                () -> createLandLordService.create(dto)
        );
    }
}
