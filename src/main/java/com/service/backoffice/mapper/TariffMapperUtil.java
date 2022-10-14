package com.service.backoffice.mapper;

import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.City;
import com.service.backoffice.repositories.CityRepo;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TariffMapperUtil {
    private final CityRepo cityRepo;

    @Named("citiesByNames")
    Set<City> citiesByNames(Set<String> cityNames) {
        Set<City> cities = new HashSet<>();
        for (String cityName : cityNames) {
            City city = cityRepo.findByNameIgnoreCase(cityName);
            if (city != null) {
                cities.add(city);
            }
        }
        if (cities.isEmpty()) {
            throw new ApiException(Exceptions.CITY_NOT_FOUND);
        }
        return cities;
    }

    @Named("namesFromCities")
    Set<String> namesFromCities(Set<City> cities) {
        Set<String> cityNames = new HashSet<>();
        for (City city : cities) {
            cityNames.add(city.getName());
        }
        return cityNames;
    }
}
