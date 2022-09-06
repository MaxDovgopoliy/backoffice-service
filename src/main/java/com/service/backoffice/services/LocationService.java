package com.service.backoffice.services;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import java.util.List;

public interface LocationService {
    List<Country> getAllCountries();

    List<City> getAllCities();

    City saveCity(CityDto cityDto);

    Country saveCountry(CountryDto countryDto);

    boolean deleteCountryById(long countryId);

    boolean deleteCityById(long cityId);

    City updateCity(long cityId, CityDto cityDto);

    Country updateCountry(long countryId, CountryDto countryDto);
}
