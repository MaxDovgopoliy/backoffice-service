package com.service.backoffice.services.implementation;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
        List<TariffDto> expectedTariffDtos = TariffMapper.MAPPER.toTariffDtos(tariffs);

        when(tariffRepo.findAll()).thenReturn(tariffs);

        var resultTariffDtos =tariffService.getAllTariffs();

        assertNotNull(resultTariffDtos);
        assertIterableEquals(expectedTariffDtos, resultTariffDtos);

    }

    @Test
    void saveTariff() {
        Tariff tariff= new Tariff(1L,"tariff1","description","sedan",120);
        TariffDto expectedTariffDto =TariffMapper.MAPPER.toTariffDto(tariff);

        when(tariffRepo.save(tariff)).thenReturn(tariff);

        var resultTariffDto = tariffService.saveTariff(expectedTariffDto);

        verify(tariffRepo).save(tariff);
        assertNotNull(resultTariffDto);
        assertEquals(expectedTariffDto, resultTariffDto);

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
        Tariff tariff= new Tariff(1L,"tariff1","description","sedan",120);
        TariffDto expectedTariffDto =TariffMapper.MAPPER.toTariffDto(tariff);
        when(tariffRepo.findById(1L)).thenReturn(Optional.of(tariff));

        var resultTariffDto = tariffService.getTariffById(1);

        verify(tariffRepo).findById(1L);
        assertNotNull(resultTariffDto);
        assertEquals(expectedTariffDto, resultTariffDto);
    }
    @Test
    void getTariffByNonExistingId() {
        //when
        var apiException = assertThrows(ApiException.class, () -> tariffService.getTariffById(1l));

        //then
        assertEquals(Exceptions.TARIFF_NOT_FOUND, apiException.getException());
    }

    @Test
    void updateTariff() {
        Tariff tariffToUpdate= new Tariff(1L,"tariff1","description","sedan",120);
        Tariff newTariff= new Tariff(null,"tariff2","description","moto",128);
        Tariff expectedTariff= new Tariff(1L,"tariff2","description","moto",128);

        when(tariffRepo.findById(1L)).thenReturn(Optional.of(tariffToUpdate));
        //when(tariffRepo.findById(1L)).thenReturn(Optional.of(tariffToUpdate));
        when(tariffRepo.save(expectedTariff)).thenReturn(expectedTariff);

        var resultTariffDto = tariffService.updateTariff(1, newTariff);

        verify(tariffRepo,times(2)).findById(1L);
        verify(tariffRepo).save(expectedTariff);
        assertNotNull(resultTariffDto);
        assertEquals(TariffMapper.MAPPER.toTariffDto(expectedTariff), resultTariffDto);

    }
    @Test
    void updateTariffWithNonExistingId() {
        Tariff newTariff= new Tariff(null,"tariff2","description","moto",128);
        when(tariffRepo.findById(1L)).thenReturn(Optional.empty());


        var apiException = assertThrows(ApiException.class,
                () -> tariffService.updateTariff(1, newTariff));

        //then
        assertEquals(Exceptions.TARIFF_NOT_FOUND, apiException.getException());
    }
}