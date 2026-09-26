package com.example.restapi.mock.entity;

import com.example.restapi.domain.model.Costumer;

public class CostumerMock {

    public static Costumer costumerMock() {
        Costumer costumer = new Costumer();

        costumer.setId(1L);
        costumer.setName("George");
        costumer.setEmail("george@gmail.com");
        costumer.setActive(true);
        costumer.setPhone("+(55) 48 99984-0909");
        costumer.setDocument("123.456.789-31");

        return costumer;
    }
}
