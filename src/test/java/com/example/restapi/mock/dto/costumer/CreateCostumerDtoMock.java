package com.example.restapi.mock.dto.costumer;

import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;

public class CreateCostumerDtoMock {

    public static CreateRequestCostumerDto createRequestCostumerDtoMock() {
        return new CreateRequestCostumerDto(
                "George",
                "123.456.789-31",
                "george@gmail.com",
                "+(55) 48 99984-0909"
        );
    }
}
