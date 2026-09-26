package com.example.restapi.service.landLord;

import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.mock.dto.landLord.UpdateRequestLandLordDtoMock;
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
public class UpdateLandLordServiceTest {

    @Mock
    private LandLordRepository landLordRepository;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @InjectMocks
    private UpdateLandLordService updateLandLordService;

    @Test
    void shouldUpdateLandLordSuccessfully() {

        UpdateRequestLandLordDto dto = UpdateRequestLandLordDtoMock.updateRequestLandLordDtoMock();
        LandLord landLord = LandLordMock.landLordMock();
        landLord.setName("Erick 1");
        landLord.setEmail("erick1@gmail.com");
        landLord.setPhone("+(55) 48 99999-9998");

        when(landLordRepository.findByEmailAndIdNot(any(String.class), any(Long.class)))
                .thenReturn(Optional.empty());

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(landLordRepository.save(any(LandLord.class)))
                .thenReturn(landLord);

        LandLord result = updateLandLordService.update(1L, dto);

        assertNotNull(result);
        assertEquals(dto.name(), result.getName());
        assertEquals(dto.email(), result.getEmail());
        assertEquals(dto.phone(), result.getPhone());
        assertTrue(result.isActive());

        verify(landLordRepository, times(1)).findByEmailAndIdNot(any(String.class), any(Long.class));
        verify(landLordRepository, times(1)).save(any(LandLord.class));
    }

    @Test
    void shouldThrowInactivatedExceptionWhenLandLordAreInactive() {

        UpdateRequestLandLordDto dto = UpdateRequestLandLordDtoMock.updateRequestLandLordDtoMock();
        LandLord landLord = LandLordMock.landLordMock();
        landLord.setActive(false);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InactivatedException.class,
                () -> updateLandLordService.update(1L, dto)
        );
    }

    @Test
    void shouldThrowConflictExceptionWhenLandLordAlreadyExistsWithEmail() {

        UpdateRequestLandLordDto dto = UpdateRequestLandLordDtoMock.updateRequestLandLordDtoMock();
        LandLord landLord = LandLordMock.landLordMock();

        LandLord landLord1 = LandLordMock.landLordMock();
        landLord1.setId(2L);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(landLordRepository.findByEmailAndIdNot(any(String.class), any(Long.class)))
                .thenReturn(Optional.of(landLord1));

        assertThrows(
                ConflictException.class,
                () -> updateLandLordService.update(1L, dto)
        );
    }
}
