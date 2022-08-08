package com.service.backoffice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.exeption.TariffNotFoundException;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Tariff;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AdminController adminController;
    @MockBean
    TariffServiceImpl tariffService;

//    public AdminControllerTest(TariffServiceImpl tariffService) {
////        MockitoAnnotations.initMocks(this);
//        this.tariffService = tariffService;
//    }

    @Test
    void addTariff() throws Exception {
        //mockMvc.perform(get("/user/tariffs")).andExpect();
    }

    @Test
    void deleteTariff() throws Exception {

        when(tariffService.deleteTariff(1)).thenReturn(true);
        mockMvc.perform(delete("/manager/delete/tariff/1")) //.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void getTariff() throws Exception {
        TariffDTO tariff1 = new TariffDTO("tariff1", "description", "sedan", 120);

        given(tariffService.getTariffById(1)).willReturn(tariff1);

        mockMvc.perform(get("/manager/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tariff1.getName()))
                .andExpect(jsonPath("$.carType").value(tariff1.getCarType()))
                .andExpect(jsonPath("$.description").value(tariff1.getDescription()))
                .andExpect(jsonPath("$.ratePerHour").value(tariff1.getRatePerHour()));

//        verify(tariffService).getTariffById(1);
    }
    @Test
    void getTariffByNonExistingId() throws Exception {

        given(tariffService.getTariffById(1)).willThrow(new TariffNotFoundException("no such tariff"));

        mockMvc.perform(get("/manager/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

//        verify(tariffService).getTariffById(1);
    }

    @Test
    void updateTariff() throws Exception {
        Tariff tariffForUpdate = new Tariff(1L, "tariff1", "description", "sedan", 120);
        TariffDTO tariffDTO = TariffMapper.MAPPER.toTariffDTO(tariffForUpdate);
        when(tariffService.updateTariff(1, tariffForUpdate)).thenReturn(tariffDTO);
        // given(tariffService.updateTariff(1, tariffForUpdate)).willReturn(tariffDTO);
        mockMvc.perform(put("/manager/update/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tariffForUpdate)))
                        .andExpect(status().isOk());
        // .andExpect(jsonPath("$.tariffDTO.name").value(tariffForUpdate.getName()));
    }

    @Test
    void getAllOrdersHistory() throws Exception {


    }
}