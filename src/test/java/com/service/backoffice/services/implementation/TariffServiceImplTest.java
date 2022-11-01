package com.service.backoffice.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.repositories.TariffRepo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
class TariffServiceImplTest {
    @InjectMocks
    private TariffServiceImpl tariffService;
    @Mock
    private TariffRepo tariffRepo;
    @Mock
    private CountryRepo countryRepo;
    @Mock
    private CityRepo cityRepo;
    @Mock
    private TariffMapper tariffMapper;

    private Country country = new Country("Ukraine", "USD", "MPH");
    private City city = new City("Lviv", 1, List.of(), country);
    private Tariff tariff1 = new Tariff("tariff1", "description", "medium", 100, "UAH");
    private Tariff tariff2 = new Tariff("tariff2", "description", "premium", 200, "UAH");


    @Test
    void getAllTariffs() {
        city.setTariffs(Set.of(tariff1, tariff2));
        country.setCities(List.of(city));
        tariff1.setCities(Set.of(city));
        tariff2.setCities(Set.of(city));

        List<Tariff> tariffs = List.of(tariff1, tariff2);


        when(tariffRepo.findAll()).thenReturn(tariffs);
        when(countryRepo.findByNameIgnoreCase(country.getName())).thenReturn(country);

        var resultTariffDtos = tariffService.getAllTariffs(country.getName(), city.getName());

        assertNotNull(resultTariffDtos);
        assertIterableEquals(tariffs, resultTariffDtos);

    }

    @Test
    void saveTariff() {
        Tariff tariff = tariff1;
        tariff.setCities(Set.of(city));
        city.setTariffs(Set.of(tariff));
        TariffDto expectedTariffDto = new TariffDto("tariff1", "description", "medium", "UAH",100,
                Collections.singleton("Lviv"));

        when(tariffRepo.save(tariff)).thenReturn(tariff);
        when(tariffMapper.toTariff(expectedTariffDto)).thenReturn(tariff);

        var resultTariff = tariffService.saveTariff(expectedTariffDto);

        verify(tariffRepo).save(tariff);
        assertNotNull(resultTariff);
        assertEquals(tariff, resultTariff);
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
        Tariff expectedTariff= new Tariff("tariff1","description","sedan",120,"UAH");
        when(tariffRepo.findById(1L)).thenReturn(Optional.of(expectedTariff));

        var resultTariffDto = tariffService.getTariffById(1);

        verify(tariffRepo).findById(1L);
        assertNotNull(resultTariffDto);
        assertEquals(expectedTariff, resultTariffDto);
    }

    @Test
    void updateTariff() {
    }

    @Test
    void getTariffForCityAndCarType() {
    }
}