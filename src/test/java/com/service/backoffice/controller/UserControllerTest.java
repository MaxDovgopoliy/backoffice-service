package com.service.backoffice.controller;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;
    @MockBean
    private TariffServiceImpl tariffService;

    @Test
    void getAllTariffs() throws Exception {

        Tariff tariff1= new Tariff("tariff1","description","sedan",120);
        Tariff tariff2= new Tariff("tariff2","description","moto",130);
        List<Tariff> tariffs= new ArrayList<>(List.of(tariff1,tariff2));
//
//        willReturn(tariff1).thenReturn(tariffService.getAllTariffs());
//
//        String expectedResponse = "{\"id\": null, \"name\":\"tariff1\", \"description\": description, \"carType\":" +
//                " sedan, \"ratePetHour\": 120}";
//
//        mockMvc.perform(get("/user/tariffs")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", hasValue("tariff1")));
    }

    @Test
    void getOrdersHistoryByUser() {
    }
}