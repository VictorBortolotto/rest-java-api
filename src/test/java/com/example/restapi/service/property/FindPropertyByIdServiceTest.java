package com.example.restapi.service.property;

import com.example.restapi.domain.exceptions.NotFoundException;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.entity.PropertyMock;
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
public class FindPropertyByIdServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private FindPropertyByIdService findPropertyByIdService;

    @Test
    void shouldReturnPropertySuccessfully() {

        Property property = PropertyMock.propertyMock();

        when(propertyRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(property));

        Property result = findPropertyByIdService.findById(1L);

        assertNotNull(result);

        verify(propertyRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenPropertyNotExists() {
        when(propertyRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> findPropertyByIdService.findById(1L)
        );
    }
}
