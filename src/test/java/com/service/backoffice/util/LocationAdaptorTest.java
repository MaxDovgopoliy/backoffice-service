package com.service.backoffice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CityDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LocationAdaptorTest {

    @InjectMocks
    LocationAdaptor locationAdaptor;
    @Mock
    CountryRepo countryRepo;
    @Mock
    CityRepo cityRepo;

    private final List<Coordinates> listOfCoordinates =
            List.of(new Coordinates(1L, 0, 2),
                    new Coordinates(2L, 0, 0),
                    new Coordinates(3L, 2, 0));
    private final Country country = new Country("Ukraine", "UAH", "KPH");
    private final City city = new City("Lviv", 1.1, listOfCoordinates, country);
    private final Area area = new Area(city, listOfCoordinates);

    @Test
    void makeCityFromDto() {

        CityDto cityDto = CityMapper.MAPPER.toCityDto(city);
        when(countryRepo.findByNameIgnoreCase(cityDto.getCountryName())).thenReturn(country);

        City result = locationAdaptor.makeCityFromDto(cityDto);
        verify(countryRepo).findByNameIgnoreCase(cityDto.getCountryName());

        assertNotNull(result);
        assertEquals(city.getName(), result.getName());
        assertEquals(city.getCountry(), result.getCountry());
        assertEquals(city.getCoefficientForTariff(), result.getCoefficientForTariff());
    }

    @Test
    void makeAreaFromDto() {
        country.setCities(List.of(city));

        AreaDto areaDto = AreaMapper.MAPPER.toAreaDto(area);

        when(countryRepo.findByNameIgnoreCase(areaDto.getCountryName())).thenReturn(country);

        var result = locationAdaptor.makeAreaFromDto(area, areaDto);
        verify(countryRepo).findByNameIgnoreCase(areaDto.getCountryName());
        assertNotNull(result);
        assertEquals(area, result);
    }

    @Test
    void checkIfAreaIsInsideAnother() {
        boolean result =
                locationAdaptor.checkIfAreaIsInsideAnother(listOfCoordinates, listOfCoordinates);
        Assertions.assertTrue(result);
    }

    @Test
    void polygonOfCoordinates() {
        Geometry geometry = LocationAdaptor.polygonOfCoordinates(listOfCoordinates);
        GeometryFactory gf = new GeometryFactory();
        Coordinate[] geomCoordinates = new Coordinate[] {
                new Coordinate(listOfCoordinates.get(0).getLatitude(),
                        listOfCoordinates.get(0).getLongitude()),
                new Coordinate(listOfCoordinates.get(1).getLatitude(),
                        listOfCoordinates.get(1).getLongitude()),
                new Coordinate(listOfCoordinates.get(2).getLatitude(),
                        listOfCoordinates.get(2).getLongitude()),
                new Coordinate(listOfCoordinates.get(0).getLatitude(),
                        listOfCoordinates.get(0).getLongitude())
        };


        Polygon polygon = gf.createPolygon(geomCoordinates);
        Assertions.assertEquals(polygon,geometry);
    }
}