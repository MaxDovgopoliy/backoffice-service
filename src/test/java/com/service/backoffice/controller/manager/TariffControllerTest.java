package com.service.backoffice.controller.manager;

import static com.service.backoffice.exception.Exceptions.TARIFF_NOT_FOUND;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.exception.ApiException;
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
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TariffControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TariffController tariffController;
    @MockBean
    TariffServiceImpl tariffService;

    @Test
    void addTariff() throws Exception {

//        Tariff tariffForAdd = new Tariff(1L, "tariff1", "description", "sedan", 120);
//        TariffDto tariffDTO = TariffMapper.MAPPER.toTariffDto(tariffForAdd);
//
//        when(tariffService.saveTariff(tariffDTO))
//                .thenReturn(tariffForAdd);
//
//        mockMvc.perform(post("/manager/tariffs")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(tariffDTO)))
//                        .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(tariffForAdd.getName()))
//                .andExpect(jsonPath("$.description").value(tariffForAdd.getDescription()))
//                .andExpect(jsonPath("$.carType").value(tariffForAdd.getCarType()))
//                .andExpect(jsonPath("$.ratePerHour").value(tariffForAdd.getRatePerHour()));
    }

    @Test
    void deleteTariff() throws Exception {

        when(tariffService.deleteTariff(1)).thenReturn(true);
        mockMvc.perform(delete("/manager/tariffs/1")) //.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void getTariff() throws Exception {
//        TariffDto tariffDto = new TariffDto("tariff", "description", "sedan", 120);
//        Tariff tariff = TariffMapper.MAPPER.toTariff(tariffDto);
//
//        given(tariffService.getTariffById(1)).willReturn(tariff);
//
//        mockMvc.perform(get("/manager/tariffs/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(tariffDto.getName()))
//                .andExpect(jsonPath("$.carType").value(tariffDto.getCarType()))
//                .andExpect(jsonPath("$.description").value(tariffDto.getDescription()))
//                .andExpect(jsonPath("$.ratePerHour").value(tariffDto.getRatePerHour()));
//
//        verify(tariffService).getTariffById(1);
    }

    @Test
    void getTariffByNonExistingId() throws Exception {

        when(tariffService.getTariffById(1L)).thenThrow(new ApiException(TARIFF_NOT_FOUND));

        mockMvc.perform(get("/manager/tariffs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorName").value(TARIFF_NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(TARIFF_NOT_FOUND.getMessage()));

        verify(tariffService).getTariffById(1);
    }

    @Test
    void updateTariff() throws Exception {
        Tariff tariffForUpdate = new Tariff(1L, "tariff1", "description", "sedan", 120);
       // when(tariffService.updateTariff(anyLong(), any(Tariff.class))).thenReturn(tariffDTO);
        when(tariffService.updateTariff(1, tariffForUpdate)).thenReturn(tariffForUpdate);

        mockMvc.perform(put("/manager/tariffs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tariffForUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tariffForUpdate.getName()));
    }
}