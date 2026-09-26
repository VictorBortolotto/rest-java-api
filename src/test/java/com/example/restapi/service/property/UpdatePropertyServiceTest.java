package com.example.restapi.service.property;

import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.dto.property.RegistryPropertyDto;
import com.example.restapi.domain.dto.property.UpdatePropertyDto;
import com.example.restapi.domain.exceptions.InactivatedException;
import com.example.restapi.domain.exceptions.InsufficentValueExcpetion;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.domain.model.Property;
import com.example.restapi.domain.repository.PropertyRepository;
import com.example.restapi.mock.dto.landLord.UpdateRequestLandLordDtoMock;
import com.example.restapi.mock.dto.property.UpdatePropertyDtoMock;
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
public class UpdatePropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private FindPropertyByIdService findPropertyByIdService;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @InjectMocks
    private UpdatePropertyService updatePropertyService;

    @Test
    void shouldUpdatePropertySuccessfully() {
        UpdatePropertyDto dto = UpdatePropertyDtoMock.updatePropertyDto();
        Property property = PropertyMock.propertyMock();
        LandLord landLord = LandLordMock.landLordMock();

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        when(propertyRepository.save(any(Property.class)))
                .thenReturn(property);

        Property result = updatePropertyService.update(1L, dto);

        assertNotNull(result);

        assertEquals(result.getLandLord().getId(), property.getLandLord().getId());

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

        verify(findPropertyByIdService, times(1)).findById(any(Long.class));
        verify(findLandLordByIdService, times(1)).findById(any(Long.class));
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void shouldThrowInactivatedExceptionWhenLandLordAreInactive() {

        UpdatePropertyDto dto = UpdatePropertyDtoMock.updatePropertyDto();
        Property property = PropertyMock.propertyMock();
        LandLord landLord = LandLordMock.landLordMock();
        landLord.setActive(false);

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InactivatedException.class,
                () -> updatePropertyService.update(1L, dto)
        );
    }

    @Test
    void shouldThrowInsufficentValueExceptionWhenCapacityEqualsZero() {

        Property property = PropertyMock.propertyMock();
        LandLord landLord = LandLordMock.landLordMock();
        UpdatePropertyDto dto = new UpdatePropertyDto(
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

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InsufficentValueExcpetion.class,
                () -> updatePropertyService.update(1L, dto)
        );
    }

    @Test
    void shouldThrowInsufficentValueExceptionWhenDailyRateEqualsZero() {

        Property property = PropertyMock.propertyMock();
        LandLord landLord = LandLordMock.landLordMock();
        UpdatePropertyDto dto = new UpdatePropertyDto(
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

        when(findPropertyByIdService.findById(any(Long.class)))
                .thenReturn(property);

        when(findLandLordByIdService.findById(any(Long.class)))
                .thenReturn(landLord);

        assertThrows(
                InsufficentValueExcpetion.class,
                () -> updatePropertyService.update(1L, dto)
        );
    }
}
