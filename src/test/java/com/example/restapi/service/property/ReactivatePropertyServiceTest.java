package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.ConflictException;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.entity.CostumerMock;
import com.example.restapi.mock.entity.PropertyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReactivatePropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindPropertyByIdService propertyByIdService;

    @InjectMocks
    private ReactivatePropertyService reactivatePropertyService;

    @Test
    void shouldReactivateCostumerSuccessfully() {
        Property property = PropertyMock.propertyMock();
        property.setActive(false);

        when(propertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(propertyRepository.save(any(Property.class)))
                .thenReturn(property);

        reactivatePropertyService.reactivate(1L);

        verify(propertyByIdService, times(1)).findById(any(Long.class));
        verify(propertyRepository, times(1)).save(any(Property.class));
    }


    @Test
    void shouldThrowConflictExceptionWhenCostumerAreActive() {

        Property property = PropertyMock.propertyMock();

        when(propertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        assertThrows(
                ConflictException.class,
                () -> reactivatePropertyService.reactivate(1L)
        );
    }

    @Test
    void shouldThrowInactivatedExceptionWhenPropertyAreInactive() {

        Property property = PropertyMock.propertyMock();
        property.setActive(false);
        property.getLandLord().setActive(false);

        when(propertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        assertThrows(
                InactivatedException.class,
                () -> reactivatePropertyService.reactivate(1L)
        );
    }
}
