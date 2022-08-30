package com.service.backoffice.services.implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CountryMapper;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.services.LocationService;
import com.service.backoffice.util.LocationAdaptor;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LocationServiceImpTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationService locationService;
    @MockBean
    CountryRepo countryRepo;
    @MockBean
    CityRepo cityRepo;
    @MockBean
    LocationAdaptor locationAdaptor;
    private static List<Country> countries =
            List.of(new Country("Ukraine", 5000),
                    new Country("Sweden", 4000));

    private static List<CountryDto> countryDtos = CountryMapper.MAPPER.toCountryDtos(countries);
    private static Country country = countries.get(0);
    private static List<City> cities =
            List.of(new City("Lviv", 500, country),
                    new City("Kyiv", 700, country),
                    new City("Lviv", 800, country));
    private static List<CityDto> cityDtos = CityMapper.MAPPER.toCityDtos(cities);
    private static City city = cities.get(0);
    private static CityDto cityDto = CityMapper.MAPPER.toCityDto(cities.get(0));
    @Test
    void getAllCountries() {
        List<Country> expectedCountries = countries;

        when(countryRepo.findAll()).thenReturn(countries);

        var resultCountries = locationService.getAllCountries();

        verify(countryRepo).findAll();
        assertNotNull(resultCountries);
        assertIterableEquals(expectedCountries, resultCountries);
    }

    @Test
    void getAllCities() {
        List<City> expectedCities = cities;

        when(cityRepo.findAll()).thenReturn(cities);

        var resultCities = locationService.getAllCities();

        verify(cityRepo).findAll();
        assertNotNull(resultCities);
        assertIterableEquals(expectedCities, resultCities);
    }

    @Test
    void saveCity() {
        City cityToSave = cities.get(0);
        CityDto cityDtoToSave = CityMapper.MAPPER.toCityDto(cityToSave);

        when(locationAdaptor.makeCityFromDto(cityDtoToSave)).thenReturn(cityToSave);
        when(cityRepo.save(cityToSave)).thenReturn(cityToSave);

        var resultCity = locationService.saveCity(cityDtoToSave);

        verify(cityRepo).save(cityToSave);
        assertNotNull(resultCity);
        assertEquals(cityToSave, resultCity);
    }

    @Test
    void saveCountry() {
        Country countryToSave = countries.get(0);
        CountryDto countryDtoToSave = CountryMapper.MAPPER.toCountryDto(countryToSave);

        when(countryRepo.findByNameIgnoreCase(countryDtoToSave.getName())).thenReturn(null);
        when(countryRepo.save(countryToSave)).thenReturn(countryToSave);

        var resultCountry = locationService.saveCountry(countryDtoToSave);

        verify(countryRepo).save(countryToSave);
        assertNotNull(resultCountry);
        assertEquals(countryToSave, resultCountry);
    }

    @Test
    void deleteCountryById() {
        doAnswer((i)-> null).when(countryRepo).deleteById(1L);
        boolean result=locationService.deleteCountryById(1L);

        assertEquals(result, true);
    }

    @Test
    void deleteCityById() {
        doAnswer((i)-> null).when(cityRepo).deleteById(1L);
        boolean result=locationService.deleteCityById(1L);

        assertEquals(result, true);
    }
}