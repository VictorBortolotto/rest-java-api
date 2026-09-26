package com.example.restapi.mock.dto.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;
import com.example.restapi.domain.dto.property.UpdatePropertyDto;

public class UpdatePropertyDtoMock {

    public static UpdatePropertyDto updatePropertyDto() {
        return new UpdatePropertyDto(
                "National Res. 1",
                "ROOM",
                "1234 Main Street",
                "1234",
                "Center",
                "Springfield",
                "Massachusetts",
                "10001",
                4,
                4.90,
                "Windows with tinted glass"
        );
    }

}
