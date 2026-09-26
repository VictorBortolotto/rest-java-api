package com.example.restapi.service.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.exceptions.InsufficentValueExcpetion;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.dto.property.RegistryPropertyDtoMock;
import com.example.restapi.mock.entity.LandLordMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.service.landLord.FindLandLordByIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistryPropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @InjectMocks
    private RegistryPropertyService registryPropertyService;

    @Test
    void shouldRegistryPropertySuccessfully() {

        RegistryPropertyDto dto = RegistryPropertyDtoMock.registryPropertyDto();
        LandLord landLord = LandLordMock.landLordMock();
        Property property = PropertyMock.propertyMock();

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(propertyRepository.save(any(Property.class)))
                .thenReturn(property);

        Property result = registryPropertyService.registry(dto);

        assertNotNull(result);

        assertEquals(dto.landLordId(), result.getLandLord().getId());

        assertEquals(dto.name(), result.getName());
        assertEquals(dto.propertyType(), result.getPropertyType().toString());
        assertEquals(dto.address(), result.getAddress());
        assertEquals(dto.number(), result.getNumber());
        assertEquals(dto.neighborhood(), result.getNeighborhood());
        assertEquals(dto.city(), result.getCity());
        assertEquals(dto.state(), result.getState());
        assertEquals(dto.zipCode(), result.getZipCode());
        assertEquals(dto.capacity(), result.getCapacity());
        assertEquals(dto.dailyRate(), result.getDailyRate());
        assertEquals(dto.notes(), result.getNotes());
        assertTrue(result.isActive());
        assertTrue(result.isAvaliable());

        verify(findLandLordByIdService, times(1)).findById(any(Long.class));
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void shouldThrowInactivatedExceptionWhenLandLordAreInactivated() {

        RegistryPropertyDto dto = RegistryPropertyDtoMock.registryPropertyDto();
        LandLord landLord = LandLordMock.landLordMock();
        landLord.setActive(false);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InactivatedException.class,
                () -> registryPropertyService.registry(dto)
        );
    }

    @Test
    void shouldThrowInsufficentValueExceptionWhenCapacityEqualsZero() {

        RegistryPropertyDto dto = new RegistryPropertyDto(
                1L,
                "National Res.",
                "ROOM",
                "1234 Main Street",
                "1234",
                "Center",
                "Springfield",
                "Massachusetts",
                "10001",
                0,
                4.90,
                "Windows with tinted glass"
        );

        LandLord landLord = LandLordMock.landLordMock();

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InsufficentValueExcpetion.class,
                () -> registryPropertyService.registry(dto)
        );
    }

    @Test
    void shouldThrowInsufficentValueExceptionWhenDailyRateEqualsZero() {

        RegistryPropertyDto dto = new RegistryPropertyDto(
                1L,
                "National Res.",
                "ROOM",
                "1234 Main Street",
                "1234",
                "Center",
                "Springfield",
                "Massachusetts",
                "10001",
                4,
                0.0,
                "Windows with tinted glass"
        );

        LandLord landLord = LandLordMock.landLordMock();

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InsufficentValueExcpetion.class,
                () -> registryPropertyService.registry(dto)
        );
    }
}
