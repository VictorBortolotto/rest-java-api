package com.example.restapi.mock.dto.costumer;

import com.example.restapi.domain.dto.costumer.UpdateRequestCostumerDto;

public class UpdateCostumerDtoMock {

    public static UpdateRequestCostumerDto updateRequestCostumerDtoMock() {
        return new UpdateRequestCostumerDto(
                "George 1",
                "george1@gmail.com",
                "+(55) 48 99986-0909"
        );
    }
}
