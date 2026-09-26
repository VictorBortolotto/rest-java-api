package com.example.restapi.mock.entity;

import com.example.restapi.domain.enums.LandLordType;
import com.example.restapi.domain.model.LandLord;

public class LandLordMock {

    public static LandLord landLordMock() {
        LandLord landLord = new LandLord();

        landLord.setId(1L);
        landLord.setLandLordType(LandLordType.HOTEL);
        landLord.setName("Erick");
        landLord.setPhone("+(55) 48 99999-9999");
        landLord.setEmail("erick@gmail.com");
        landLord.setDocument("123.456.789-32");
        landLord.setActive(true);

        return landLord;
    }
}
