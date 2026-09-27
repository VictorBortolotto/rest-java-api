package com.example.restapi.controller.costumer;

import com.example.restapi.domain.dto.costumer.CreateRequestCostumerDto;
import com.example.restapi.domain.dto.costumer.UpdateRequestCostumerDto;
import com.example.restapi.domain.model.Costumer;
import com.example.restapi.mock.dto.costumer.CreateCostumerDtoMock;
import com.example.restapi.mock.dto.costumer.UpdateCostumerDtoMock;
import com.example.restapi.mock.entity.CostumerMock;
import com.example.restapi.service.costumer.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CostumerControllerTest {

    @Mock
    private CreateCostumerService createCostumerService;

    @Mock
    private FindAllCostumerService findAllCostumerService;

    @Mock
    private FindCostumerByIdService findCostumerByIdService;

    @Mock
    private UpdateCostumerService updateCostumerService;

    @Mock
    private ReactivateCostumerService reactivateCostumerService;

    @Mock
    private InactivateCostumerService inactivateCostumerService;

    @InjectMocks
    private CostumerController costumerController;


    @Test
    void shouldCreateCostumer() {

        CreateRequestCostumerDto dto = CreateCostumerDtoMock.createRequestCostumerDtoMock();

        Costumer costumer = CostumerMock.costumerMock();

        when(createCostumerService.create(dto))
                .thenReturn(costumer);

        Costumer result = costumerController.create(dto);

        assertNotNull(result);
        assertEquals(costumer, result);

        verify(createCostumerService)
                .create(dto);
    }


    @Test
    void shouldFindAllCostumers() {

        Costumer costumer1 = CostumerMock.costumerMock();
        Costumer costumer2 = new Costumer();
        costumer2.setId(2L);
        costumer2.setName("Wendell");
        costumer2.setEmail("wendell@gmail.com");
        costumer2.setActive(true);
        costumer2.setPhone("+(55) 48 99936-1999");
        costumer2.setDocument("123.456.749-34");

        List<Costumer> costumers =
                List.of(costumer1, costumer2);

        when(findAllCostumerService.findAll(true))
                .thenReturn(costumers);

        List<Costumer> result =
                costumerController.findAll(true);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(costumers, result);

        verify(findAllCostumerService)
                .findAll(true);
    }


    @Test
    void shouldFindAllCostumersWithoutActiveFilter() {

        List<Costumer> costumers =
                List.of(CostumerMock.costumerMock());

        when(findAllCostumerService.findAll(null))
                .thenReturn(costumers);

        List<Costumer> result =
                costumerController.findAll(null);

        assertNotNull(result);
        assertEquals(costumers, result);

        verify(findAllCostumerService)
                .findAll(null);
    }


    @Test
    void shouldFindCostumerById() {

        long id = 1L;

        Costumer costumer = CostumerMock.costumerMock();

        when(findCostumerByIdService.findById(id))
                .thenReturn(costumer);

        Costumer result =
                costumerController.findById(id);

        assertNotNull(result);
        assertEquals(costumer, result);

        verify(findCostumerByIdService)
                .findById(id);
    }


    @Test
    void shouldUpdateCostumer() {

        long id = 1L;

        UpdateRequestCostumerDto dto = UpdateCostumerDtoMock.updateRequestCostumerDtoMock();

        Costumer costumer = CostumerMock.costumerMock();

        when(updateCostumerService.update(id, dto))
                .thenReturn(costumer);

        Costumer result =
                costumerController.update(id, dto);

        assertNotNull(result);
        assertEquals(costumer, result);

        verify(updateCostumerService)
                .update(id, dto);
    }


    @Test
    void shouldInactivateCostumer() {

        long id = 1L;

        doNothing()
                .when(inactivateCostumerService)
                .inactivate(id);

        costumerController.inactivate(id);

        verify(inactivateCostumerService)
                .inactivate(id);
    }

    @Test
    void shouldReactivateCostumer() {

        long id = 1L;

        doNothing()
                .when(reactivateCostumerService)
                .reactivate(id);

        costumerController.reactivate(id);

        verify(reactivateCostumerService)
                .reactivate(id);
    }
}