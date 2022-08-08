package com.service.backoffice.controller;

import com.service.backoffice.controller.user.UserController;
import com.service.backoffice.dto.TariffDTO;
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
import static org.mockito.BDDMockito.given;
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

        TariffDTO tariff1= new TariffDTO("tariff1","description","sedan",120);
        TariffDTO tariff2= new TariffDTO("tariff2","description","moto",130);
        TariffDTO tariff3= new TariffDTO("tariff3","description","moto",135);
        List<TariffDTO> tariffs= new ArrayList<>(List.of(tariff1,tariff2,tariff3));

        given(tariffService.getAllTariffs()).willReturn(tariffs);


        mockMvc.perform(get("/user/tariffs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tariffs.size())))
                .andExpect(jsonPath("$[0].name").value(tariffs.get(0).getName()))
                .andExpect(jsonPath("$[1].carType").value(tariffs.get(1).getCarType()))
                .andExpect(jsonPath("$[2].ratePerHour").value(tariffs.get(2).getRatePerHour()));
    }

    @Test
    void getOrdersHistoryByUser() {
    }
}