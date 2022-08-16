package com.service.backoffice.controller.manager;

import static com.service.backoffice.exeption.Exceptions.BAD_TARIFF_CREDENTIALS;
import static com.service.backoffice.exeption.Exceptions.TARIFF_NOT_FOUND;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.exeption.ApiException;
import com.service.backoffice.mapper.TariffMapper;
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

        Tariff tariffForAdd = new Tariff(1L, "tariff1", "description", "sedan", 120);
        TariffDto tariffDTO = TariffMapper.MAPPER.toTariffDto(tariffForAdd);

        when(tariffService.saveTariff("tariff1", "", "sedan", 120))
                .thenReturn(tariffDTO);

        mockMvc.perform(post("/manager/tariff/add")
                        .param("name","tariff1")
                        .param("ratePerHour","120")
                        .param("carType","sedan"))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tariffForAdd.getName()))
                .andExpect(jsonPath("$.description").value(tariffForAdd.getDescription()))
                .andExpect(jsonPath("$.carType").value(tariffForAdd.getCarType()))
                .andExpect(jsonPath("$.ratePerHour").value(tariffForAdd.getRatePerHour()));
    }
    @Test
    void addTariffWithoutName() throws Exception {

        Tariff tariffForAdd = new Tariff(1L, "tariff1", "description", "sedan", 120);
        TariffDto tariffDTO = TariffMapper.MAPPER.toTariffDto(tariffForAdd);

        when(tariffService.saveTariff(null, "", "sedan", 120))
                .thenThrow(new ApiException(BAD_TARIFF_CREDENTIALS));

        mockMvc.perform(post("/manager/tariff/add")
                        .param("ratePerHour","120")
                        .param("carType","sedan")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());

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
        TariffDto tariff1 = new TariffDto("tariff1", "description", "sedan", 120);

        given(tariffService.getTariffById(1)).willReturn(tariff1);

        mockMvc.perform(get("/manager/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tariff1.getName()))
                .andExpect(jsonPath("$.carType").value(tariff1.getCarType()))
                .andExpect(jsonPath("$.description").value(tariff1.getDescription()))
                .andExpect(jsonPath("$.ratePerHour").value(tariff1.getRatePerHour()));

        verify(tariffService).getTariffById(1);
    }

    @Test
    void getTariffByNonExistingId() throws Exception {

        when(tariffService.getTariffById(1L)).thenThrow(new ApiException(TARIFF_NOT_FOUND));

        mockMvc.perform(get("/manager/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorName").value(TARIFF_NOT_FOUND.name()))
                .andExpect(jsonPath("$.message").value(TARIFF_NOT_FOUND.getMessage()));

        verify(tariffService).getTariffById(1);
    }

    @Test
    void updateTariff() throws Exception {
        Tariff tariffForUpdate = new Tariff(1L, "tariff1", "description", "sedan", 120);
        TariffDto tariffDTO = TariffMapper.MAPPER.toTariffDto(tariffForUpdate);
       // when(tariffService.updateTariff(anyLong(), any(Tariff.class))).thenReturn(tariffDTO);
        when(tariffService.updateTariff(1, tariffForUpdate)).thenReturn(tariffDTO);

        mockMvc.perform(put("/manager/update/tariff/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tariffForUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tariffForUpdate.getName()));
    }
}