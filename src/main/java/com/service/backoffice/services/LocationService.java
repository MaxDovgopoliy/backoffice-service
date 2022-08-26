package com.service.backoffice.services;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import java.util.List;

public interface LocationService {
    List<Country> getAllCountries();

    List<City> getAllCities();

    City saveCity(CityDto cityDto);
}
