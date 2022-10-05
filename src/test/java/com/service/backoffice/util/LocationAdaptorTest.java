package com.service.backoffice.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CityDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
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
class LocationAdaptorTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationAdaptor locationAdaptor;
    @MockBean
    CountryRepo countryRepo;
    @MockBean
    CityRepo cityRepo;

    @Test
    void makeCityFromDto() {
        Country country = new Country("Ukraine");
        City city = new City("Lviv", 500, country);
        CityDto cityDto = CityMapper.MAPPER.toCityDto(city);
        when(countryRepo.findByNameIgnoreCase(cityDto.getCountryName())).thenReturn(country);

        var result = locationAdaptor.makeCityFromDto(cityDto);
        verify(countryRepo).findByNameIgnoreCase(cityDto.getCountryName());
        assertNotNull(result);
        assertEquals(city, result);
    }

    @Test
    void makeAreaFromDto() {
        Country country = new Country("Ukraine");
        City city = new City("Lviv", 500, country);

        List<Area> areas =
                List.of(new Area(240, new Address("Shevchenka",21), city),
                        new Area(240, new Address("Shevchenka",22), city),
                        new Area(240, new Address("Shevchenka",23), city));
        country.setCities(List.of(city));

        Area expectedArea = areas.get(0);
        Area area = new Area();
        AreaDto areaDto = AreaMapper.MAPPER.toAreaDto(expectedArea);

        when(countryRepo.findByNameIgnoreCase(areaDto.getCountryName())).thenReturn(country);
        // when(country.getCities()).thenReturn(List.of(city));

        var result = locationAdaptor.makeAreaFromDto(area, areaDto);
        verify(countryRepo).findByNameIgnoreCase(areaDto.getCountryName());
        assertNotNull(result);
        assertEquals(expectedArea, result);
    }
}