package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.services.LocationService;
import com.service.backoffice.util.LocationConvertor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private LocationConvertor locationConvertor;

    @Override
    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    @Override
    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    @Override
    public City saveCity(CityDto cityDto) {
        City city = locationConvertor.makeCityFromDto(cityDto);
        return cityRepo.save(city);
    }

}
