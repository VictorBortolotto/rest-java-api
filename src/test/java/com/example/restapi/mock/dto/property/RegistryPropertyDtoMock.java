package com.example.restapi.mock.dto.property;

import com.example.restapi.domain.dto.property.RegistryPropertyDto;

public class RegistryPropertyDtoMock {

    public static RegistryPropertyDto registryPropertyDto() {
        return new RegistryPropertyDto(
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
                4.90,
                "Windows with tinted glass"
        );
    }

}
