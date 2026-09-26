package com.example.restapi.mock.entity;

import com.example.restapi.domain.enums.PropertyType;
import com.example.restapi.domain.model.Property;

public class PropertyMock {

    public static Property propertyMock() {
        Property property = new Property();

        property.setId(1L);
        property.setLandLord(LandLordMock.landLordMock());
        property.setName("National Res.");
        property.setPropertyType(PropertyType.ROOM);
        property.setAddress("1234 Main Street");
        property.setNumber("1234");
        property.setNeighborhood("Center");
        property.setCity("Springfield");
        property.setState("Massachusetts");
        property.setZipCode("10001");
        property.setCapacity(4);
        property.setDailyRate(4.90);
        property.setActive(true);
        property.setAvaliable(true);
        property.setNotes("Windows with tinted glass");

        return property;
    }
}
