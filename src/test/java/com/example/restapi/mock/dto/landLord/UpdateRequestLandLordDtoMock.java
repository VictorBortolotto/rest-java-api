package com.example.restapi.mock.dto.landLord;

import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;

public class UpdateRequestLandLordDtoMock {

    public static UpdateRequestLandLordDto updateRequestLandLordDtoMock() {
        return new UpdateRequestLandLordDto(
                "Erick 1",
                "erick1@gmail.com",
                "+(55) 48 99999-9998"
        );
    }
}
