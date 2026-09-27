package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.mock.entity.LandLordMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReactivateLandLordServiceTest {

    @Mock
    private LandLordRepository landLordRepository;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @InjectMocks
    private ReactivateLandLordService reactivateLandLordService;

    @Test
    void shouldReactivatePropertySuccessfully() {
        LandLord landLord = LandLordMock.landLordMock();
        landLord.setActive(false);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(landLordRepository.save(any(LandLord.class)))
                .thenReturn(landLord);

        reactivateLandLordService.reactivate(1L);

        verify(findLandLordByIdService, times(1)).findById(any(Long.class));
        verify(landLordRepository, times(1)).save(any(LandLord.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenPropertyAreActive() {

        LandLord landLord = LandLordMock.landLordMock();

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                ConflictException.class,
                () -> reactivateLandLordService.reactivate(1L)
        );
    }
}
