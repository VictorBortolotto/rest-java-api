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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindLandLordByIdServiceTest {

    @Mock
    private LandLordRepository landLordRepository;

    @InjectMocks
    private FindLandLordByIdService findLandLordByIdService;

    @Test
    void shouldReturnLandLordSuccessfully() {

        LandLord landLord = LandLordMock.landLordMock();

        when(landLordRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(landLord));

        LandLord result = findLandLordByIdService.findById(1L);

        assertNotNull(result);

        verify(landLordRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDocumentAlreadyExists() {
        when(landLordRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> findLandLordByIdService.findById(1L)
        );
    }
}
