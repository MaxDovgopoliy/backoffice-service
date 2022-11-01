package com.service.backoffice.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CoordinatesMapper;
import com.service.backoffice.mapper.CountryMapper;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.util.LocationAdaptor;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LocationServiceImpTest {
    @InjectMocks
    LocationServiceImp locationService;
    @Mock
    CountryRepo countryRepo;
    @Mock
    CityRepo cityRepo;
    @Mock
    LocationAdaptor locationAdaptor;
    @Mock
    CountryMapper countryMapper;
    @Mock
    CityMapper cityMapper;
    @Mock
    CoordinatesMapper coordinatesMapper;

    private final List<Coordinates> listOfCoordinates =
            List.of(new Coordinates(1L, 0, 2),
                    new Coordinates(2L, 0, 0),
                    new Coordinates(3L, 2, 0));
    private static List<Country> countries =
            List.of(new Country("Ukraine", "UAH", "KPH"),
                    new Country("Sweden", "USD", "MPH"));
    private static Country country = countries.get(0);

    private static List<City> cities =
            List.of(new City("Lviv", 1.1, List.of(new Coordinates(1L, 0, 2),
                            new Coordinates(2L, 0, 0),
                            new Coordinates(3L, 2, 0)), country),
                    new City("Kyiv", 1.2,List.of(new Coordinates(1L, 5, 0),
                            new Coordinates(2L, 5, 5),
                            new Coordinates(3L, 7, 0)), country));

    private static List<CityDto> cityDtos = CityMapper.MAPPER.toCityDtos(cities);

    private static List<CountryDto> countryDtos = CountryMapper.MAPPER.toCountryDtos(countries);
    private static City city = cities.get(0);
    private static CityDto cityDto = CityMapper.MAPPER.toCityDto(cities.get(0));
    {
        country.setCities(cities);
    }

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
        when(countryRepo.save(any(Country.class))).thenReturn(countryToSave);


        var resultCountry = locationService.saveCountry(countryDtoToSave);
        verify(countryRepo).save(any(Country.class));
        assertNotNull(resultCountry);
        assertEquals(countryToSave.getName(), resultCountry.getName());
        assertEquals(countryToSave.getUnitOfSpeed(), resultCountry.getUnitOfSpeed());
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

    @Test
    void updateCity() {
        CityDto newCityDto = new CityDto();
        newCityDto.setCountryName(cityDto.getCountryName());
        newCityDto.setCoordinatesDtoList(cityDto.getCoordinatesDtoList());
        newCityDto.setCoefficientForTariff(cityDto.getCoefficientForTariff());
        newCityDto.setName("LVIV");

        when(cityRepo.findById(1L)).thenReturn(Optional.ofNullable(city));
        when(cityRepo.save(any(City.class))).thenReturn(city);
        var result = locationService.updateCity(1, newCityDto);

        verify(cityRepo,times(2)).findById(1L);
        assertNotNull(result);
        assertEquals(newCityDto.getName(), result.getName());
    }

    @Test
    void updateCountry() {
        CountryDto newCountry= new CountryDto();
        newCountry.setName("UKRAINE");
        newCountry.setCurrency(country.getName());
        newCountry.setUnitOfSpeed(country.getName());

        when(countryRepo.findById(1L)).thenReturn(Optional.ofNullable(country));
        when(countryRepo.save(any(Country.class))).thenReturn(country);
        var result = locationService.updateCountry(1, newCountry);

        verify(countryRepo,times(2)).findById(1L);
        assertNotNull(result);
        assertEquals(newCountry.getName(), result.getName());
    }
}