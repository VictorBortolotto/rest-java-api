package com.example.restapi.controller.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;
import com.example.restapi.domain.dto.property.UpdatePropertyDto;
import com.example.restapi.domain.model.Property;
import com.example.restapi.mock.dto.property.RegistryPropertyDtoMock;
import com.example.restapi.mock.dto.property.UpdatePropertyDtoMock;
import com.example.restapi.mock.entity.PropertyMock;
import com.example.restapi.service.property.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyControllerTest {

    @Mock
    private RegistryPropertyService registryPropertyService;

    @Mock
    private FindAllPropertyService findAllPropertyService;

    @Mock
    private FindPropertyByIdService findPropertyByIdService;

    @Mock
    private InactivatePropertyService inactivatePropertyService;

    @Mock
    private ReactivatePropertyService reactivatePropertyService;

    @Mock
    private UpdatePropertyService updatePropertyService;

    @InjectMocks
    private PropertyController propertyController;


    @Test
    void shouldRegistryProperty() {

        RegistryPropertyDto dto = RegistryPropertyDtoMock.registryPropertyDto();

        Property property = PropertyMock.propertyMock();

        when(registryPropertyService.registry(dto))
                .thenReturn(property);

        Property result =
                propertyController.registry(dto);

        assertNotNull(result);
        assertEquals(property, result);

        verify(registryPropertyService)
                .registry(dto);
    }


    @Test
    void shouldFindAllProperties() {

        Long id = 1L;
        Long landLordId = 2L;
        Boolean isAvaliable = true;
        Boolean isActive = true;

        Property property1 = PropertyMock.propertyMock();
        Property property2 = PropertyMock.propertyMock();
        property2.setId(2L);

        List<Property> properties =
                List.of(property1, property2);

        when(findAllPropertyService.findAll(
                id,
                landLordId,
                isAvaliable,
                isActive
        )).thenReturn(properties);

        List<Property> result =
                propertyController.findAll(
                        id,
                        landLordId,
                        isAvaliable,
                        isActive
                );

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(properties, result);

        verify(findAllPropertyService)
                .findAll(
                        id,
                        landLordId,
                        isAvaliable,
                        isActive
                );
    }


    @Test
    void shouldFindAllPropertiesWithoutFilters() {

        when(findAllPropertyService.findAll(
                null,
                null,
                null,
                null
        )).thenReturn(List.of(PropertyMock.propertyMock()));

        List<Property> result =
                propertyController.findAll(
                        null,
                        null,
                        null,
                        null
                );

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(findAllPropertyService)
                .findAll(
                        null,
                        null,
                        null,
                        null
                );
    }


    @Test
    void shouldFindPropertyById() {

        long id = 1L;

        Property property = PropertyMock.propertyMock();

        when(findPropertyByIdService.findById(id))
                .thenReturn(property);

        Property result =
                propertyController.findById(id);

        assertNotNull(result);
        assertEquals(property, result);

        verify(findPropertyByIdService)
                .findById(id);
    }


    @Test
    void shouldInactivateProperty() {

        long id = 1L;

        propertyController.inactivate(id);

        verify(inactivatePropertyService)
                .inactivate(id);
    }

    @Test
    void shouldReactivateProperty() {

        long id = 1L;

        doNothing()
                .when(reactivatePropertyService)
                .reactivate(id);

        propertyController.reactivate(id);

        verify(reactivatePropertyService)
                .reactivate(id);
    }


    @Test
    void shouldUpdateProperty() {

        long id = 1L;

        UpdatePropertyDto dto = UpdatePropertyDtoMock.updatePropertyDto();

        Property property = new Property();

        when(updatePropertyService.update(id, dto))
                .thenReturn(property);

        Property result =
                propertyController.update(id, dto);

        assertNotNull(result);
        assertEquals(property, result);

        verify(updatePropertyService)
                .update(id, dto);
    }
}