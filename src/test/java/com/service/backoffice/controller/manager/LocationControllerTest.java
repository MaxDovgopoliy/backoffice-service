package com.service.backoffice.controller.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.services.LocationService;
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
class LocationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    LocationController locationController;

    @MockBean
    LocationService locationService;
//    private static List<Country> countries =
//            List.of(new Country("Ukraine"),
//                    new Country("Sweden"));
//
//    private static List<CountryDto> countryDtos = CountryMapper.MAPPER.toCountryDtos(countries);
//    private static Country country = countries.get(0);
//    private static CountryDto countryDto = CountryMapper.MAPPER.toCountryDto(countries.get(0));
//    private static List<City> cities =
//            List.of(new City("Lviv", 500, country),
//                    new City("Kyiv", 700, country),
//                    new City("Lviv", 800, country));
//    private static List<CityDto> cityDtos = CityMapper.MAPPER.toCityDtos(cities);
//    private static City city = cities.get(0);
//    private static CityDto cityDto = CityMapper.MAPPER.toCityDto(cities.get(0));

    @Test
    void addCity() throws Exception {
//        when(locationService.saveCity(cityDto))
//                .thenReturn(city);
//
//        mockMvc.perform(post("/manager/cities")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(cityDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.countryName").value(cityDto.getCountryName()))
//                .andExpect(jsonPath("$.name").value(cityDto.getName()))
//                .andExpect(jsonPath("$.square").value(cityDto.getSquare()));
//
//        verify(locationService).saveCity(cityDto);
    }

    @Test
    void addCountry() throws Exception {
//        when(locationService.saveCountry(countryDto))
//                .thenReturn(country);
//
//        mockMvc.perform(post("/manager/countries")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(countryDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value(countryDto.getName()));
//
//        verify(locationService).saveCountry(countryDto);
    }

    @Test
    void deleteCountry() throws Exception {
//        when(locationService.deleteCountryById(1)).thenReturn(true);
//        mockMvc.perform(delete("/manager/countries/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//        verify(locationService).deleteCountryById(1);
    }

    @Test
    void deleteCity() throws Exception {
//        when(locationService.deleteCityById(1)).thenReturn(true);
//        mockMvc.perform(delete("/manager/cities/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//        verify(locationService).deleteCityById(1);
    }
}