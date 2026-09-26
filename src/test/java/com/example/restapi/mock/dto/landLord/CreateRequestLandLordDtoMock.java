package com.example.restapi.mock.dto.landLord;

import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;

public class CreateRequestLandLordDtoMock {

    public static CreateRequestLandLordDto createRequestLandLordDtoMock() {
        return new CreateRequestLandLordDto(
                "Erick",
                "123.456.789-32",
                "HOTEL",
                "erick@gmail.com",
                "+(55) 48 99999-9999"
        );
    }
}
