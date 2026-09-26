package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.entity.LandLordMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.service.landLord.InactivateLandLordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InactivatePropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindPropertyByIdService findPropertyByIdService;

    @InjectMocks
    private InactivatePropertyService inactivatePropertyService;

    @Test
    void shouldDeactivatePropertySuccessfully() {

        Property property = PropertyMock.propertyMock();

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(propertyRepository.save(any(Property.class)))
                .thenReturn(property);

        inactivatePropertyService.inactivate(1L);

        verify(findPropertyByIdService, times(1)).findById(any(Long.class));
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void shouldThrowConflictExceptionWhenPropertyAreInactive() {

        Property property = PropertyMock.propertyMock();
        property.setActive(false);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        assertThrows(
                ConflictException.class,
                () -> inactivatePropertyService.inactivate(1L)
        );
    }
}
