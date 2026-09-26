package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.entity.LandLordMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.service.landLord.FindLandLordByIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllPropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @InjectMocks
    private FindAllPropertyService findAllPropertyService;

    @Test
    void shouldReturnPropertySuccessfully() {

        Property property = PropertyMock.propertyMock();
        LandLord landLord = LandLordMock.landLordMock();

        when(propertyRepository.findAll(any(Long.class),any(Long.class),any(Boolean.class),any(Boolean.class)))
                .thenReturn(List.of(property));

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        List<Property> result = findAllPropertyService.findAll(1L, 1L, true, true);

        assertNotNull(result);

        verify(propertyRepository, times(1)).findAll(any(Long.class),any(Long.class),any(Boolean.class),any(Boolean.class));
        verify(findLandLordByIdService, times(1)).findById(any(Long.class));
    }

    @Test
    void shouldReturnPropertyWhenLandLordIsNullSuccessfully() {

        Property property = PropertyMock.propertyMock();

        when(propertyRepository.findAll(any(Long.class),any(),any(Boolean.class),any(Boolean.class)))
                .thenReturn(List.of(property));

        List<Property> result = findAllPropertyService.findAll(1L, null, true, true);

        assertNotNull(result);

        verify(propertyRepository, times(1)).findAll(any(Long.class),any(),any(Boolean.class),any(Boolean.class));
        verify(findLandLordByIdService, times(0)).findById(any(Long.class));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenPropertyNotExists() {
        when(propertyRepository.findAll(any(Long.class),any(Long.class),any(Boolean.class),any(Boolean.class)))
                .thenReturn(Collections.emptyList());

        LandLord landLord = LandLordMock.landLordMock();

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                NotFoundException.class,
                () -> findAllPropertyService.findAll(1L, 1L, true, true)
        );
    }
}
