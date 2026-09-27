package com.example.restapi.controller.landLord;

import com.example.restapi.domain.dto.landLord.CreateRequestLandLordDto;
import com.example.restapi.domain.dto.landLord.UpdateRequestLandLordDto;
import com.example.restapi.domain.enums.LandLordType;
import com.example.restapi.domain.model.LandLord;
import com.example.restapi.mock.dto.landLord.CreateRequestLandLordDtoMock;
import com.example.restapi.mock.dto.landLord.UpdateRequestLandLordDtoMock;
import com.example.restapi.mock.entity.LandLordMock;
import com.example.restapi.service.landLord.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LandLordControllerTest {

    @Mock
    private CreateLandLordService createLandLordService;

    @Mock
    private FindAllLandLordService findAllLandLordService;

    @Mock
    private FindLandLordByIdService findLandLordByIdService;

    @Mock
    private UpdateLandLordService updateLandLordService;

    @Mock
    private InactivateLandLordService inactivateLandLordService;

    @Mock
    private ReactivateLandLordService reactivateLandLordService;

    @InjectMocks
    private LandLordController landLordController;


    @Test
    void shouldCreateLandLord() {

        CreateRequestLandLordDto dto = CreateRequestLandLordDtoMock.createRequestLandLordDtoMock();

        LandLord landLord = LandLordMock.landLordMock();

        when(createLandLordService.create(dto))
                .thenReturn(landLord);

        LandLord result =
                landLordController.create(dto);

        assertNotNull(result);
        assertEquals(landLord, result);

        verify(createLandLordService)
                .create(dto);
    }


    @Test
    void shouldFindAllLandLords() {

        LandLord landLord1 = LandLordMock.landLordMock();
        LandLord landLord2 = new LandLord();
        landLord2.setId(2L);
        landLord2.setLandLordType(LandLordType.HOTEL);
        landLord2.setName("Wendell");
        landLord2.setPhone("+(55) 48 99999-9699");
        landLord2.setEmail("wendell@gmail.com");
        landLord2.setDocument("123.456.745-38");
        landLord2.setActive(true);

        List<LandLord> landLords =
                List.of(landLord1, landLord2);

        when(findAllLandLordService.findAll())
                .thenReturn(landLords);

        List<LandLord> result =
                landLordController.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(landLords, result);

        verify(findAllLandLordService)
                .findAll();
    }


    @Test
    void shouldFindLandLordById() {

        long id = 1L;

        LandLord landLord = LandLordMock.landLordMock();

        when(findLandLordByIdService.findById(id))
                .thenReturn(landLord);

        LandLord result =
                landLordController.findById(id);

        assertNotNull(result);
        assertEquals(landLord, result);

        verify(findLandLordByIdService)
                .findById(id);
    }


    @Test
    void shouldUpdateLandLord() {

        long id = 1L;

        UpdateRequestLandLordDto dto = UpdateRequestLandLordDtoMock.updateRequestLandLordDtoMock();

        LandLord landLord = LandLordMock.landLordMock();

        when(updateLandLordService.update(id, dto))
                .thenReturn(landLord);

        LandLord result =
                landLordController.update(id, dto);

        assertNotNull(result);
        assertEquals(landLord, result);

        verify(updateLandLordService)
                .update(id, dto);
    }


    @Test
    void shouldInactivateLandLord() {

        long id = 1L;

        landLordController.inactivate(id);

        verify(inactivateLandLordService)
                .inactivate(id);
    }

    @Test
    void shouldReactivateLandLord() {

        long id = 1L;

        doNothing()
                .when(reactivateLandLordService)
                .reactivate(id);

        landLordController.reactivate(id);

        verify(reactivateLandLordService)
                .reactivate(id);
    }
}
