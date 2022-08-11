package com.service.backoffice.services.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.controller.manager.TariffController;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TariffServiceImplTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TariffServiceImpl tariffService;
    @MockBean
    TariffRepo tariffRepo;

    @Test
    void getAllTariffs() {
        Tariff tariff1= new Tariff(1L,"tariff1","description","sedan",120);
        Tariff tariff2= new Tariff(2L,"tariff2","description","moto",130);
        Tariff tariff3= new Tariff(3L,"tariff3","description","moto",135);
        List<Tariff> tariffs= new ArrayList<>(List.of(tariff1,tariff2,tariff3));
        List<TariffDTO>expectedTariffDTOs= TariffMapper.MAPPER.toTariffDTOs(tariffs);

        when(tariffRepo.findAll()).thenReturn(tariffs);

        List<TariffDTO>resultTariffDTOS=tariffService.getAllTariffs();

        assertNotNull(resultTariffDTOS);
        assertIterableEquals(expectedTariffDTOs, resultTariffDTOS);

    }

    @Test
    void saveTariff() {
        Tariff tariff= new Tariff(1L,"tariff1","description","sedan",120);
        TariffDTO expectedTariffDTO=TariffMapper.MAPPER.toTariffDTO(tariff);

        when(tariffRepo.save(tariff)).thenReturn(tariff);

        TariffDTO resultTariffDTO= tariffService.saveTariff(tariff.getName(),
                     tariff.getDescription(), tariff.getCarType(), tariff.getRatePerHour());

        verify(tariffRepo).save(tariff);
        assertNotNull(resultTariffDTO);
        assertEquals(expectedTariffDTO,resultTariffDTO);

    }

    @Test
    void deleteTariff() {
        doAnswer((i)->  {return null;}).when(tariffRepo).deleteById(1L);
        boolean result=tariffService.deleteTariff(1L);

        assertEquals(result, true);
    }
    @Test
    void deleteTariff_WithNonExistingId() {
        doThrow(new EmptyResultDataAccessException(1)).when(tariffRepo).deleteById(1L);
        boolean result=tariffService.deleteTariff(1L);

        assertEquals(result, false);
    }

    @Test
    void getTariffById() {
    }

    @Test
    void updateTariff() {
    }
}