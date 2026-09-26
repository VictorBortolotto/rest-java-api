package com.example.restapi.service.landLord;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.LandLordRepository;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.entity.LandLordMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.service.property.FindAllPropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InactivateLandLordServiceTest {

    @Mock
    private LandLordRepository landLordRepository;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindAllPropertyService findAllPropertyService;

    @InjectMocks
    private InactivateLandLordService inactivateLandLordService;

    @Test
    void shouldDeactivateLandLordSuccessfully() {

        LandLord landLord = LandLordMock.landLordMock();

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(findAllPropertyService.findAll(any(), any(Long.class), any(), any()))
                .thenReturn(Collections.emptyList());

        when(landLordRepository.save(any(LandLord.class)))
                .thenReturn(landLord);

        inactivateLandLordService.inactivate(1L);

        verify(findLandLordByIdService, times(1)).findById(any(Long.class));
        verify(findAllPropertyService, times(1)).findAll(any(), any(Long.class), any(), any());
        verify(landLordRepository, times(1)).save(any(LandLord.class));
    }

    @Test
    void shouldDeactivateLandLordAndPropertiesSuccessfully() {

        LandLord landLord = LandLordMock.landLordMock();
        List<Property> properties = List.of(PropertyMock.propertyMock());

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(findAllPropertyService.findAll(any(), any(Long.class), any(), any()))
                .thenReturn(properties);

        when(landLordRepository.save(any(LandLord.class)))
                .thenReturn(landLord);

        when(propertyRepository.saveAll(any(List.class))).thenReturn(properties);

        inactivateLandLordService.inactivate(1L);

        verify(findLandLordByIdService, times(1)).findById(any(Long.class));
        verify(findAllPropertyService, times(1)).findAll(any(), any(Long.class), any(), any());
        verify(landLordRepository, times(1)).save(any(LandLord.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenLandLordAreInactive() {

        LandLord landLord = LandLordMock.landLordMock();
        landLord.setActive(false);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                ConflictException.class,
                () -> inactivateLandLordService.inactivate(1L)
        );
    }

}
