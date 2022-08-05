package com.service.backoffice.controller;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.TariffService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AdminController adminController;
    @Mock
    TariffServiceImpl tariffService;

    public AdminControllerTest(TariffServiceImpl tariffService) {
//        MockitoAnnotations.initMocks(this);
        this.tariffService = tariffService;
    }

    @Test
    void addTariff() throws Exception {
        //mockMvc.perform(get("/user/tariffs")).andExpect();
    }

    @Test
    void deleteTariff() {
    }

    @Test
    void getTariff() throws Exception {
        Tariff tariff1= new Tariff(1L,"tariff1","description","sedan",120);

        given(tariffService.getTariffById(1)).willReturn(tariff1);

        mockMvc.perform(get("/manager/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateTariff() {
    }

    @Test
    void getAllOrdersHistory() {
    }
}