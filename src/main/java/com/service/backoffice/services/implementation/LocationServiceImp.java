package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.services.LocationService;
import com.service.backoffice.util.LocationAdaptor;
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
    private LocationAdaptor locationAdaptor;

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
        City city = locationAdaptor.makeCityFromDto(cityDto);
        return cityRepo.save(city);
    }

    @Override
    public Country saveCountry(CountryDto countryDto) {
        if (countryRepo.findByNameIgnoreCase(countryDto.getName()) == null) {
            Country country = new Country(countryDto.getName());
            return countryRepo.save(country);
        } else {
            throw new ApiException(Exceptions.COUNTRY_ALREADY_EXIST);
        }
    }

    @Override
    public boolean deleteCountryById(long countryId) {
        try {
            countryRepo.deleteById(countryId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCityById(long cityId) {
        try {
            cityRepo.deleteById(cityId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public City updateCity(long cityId, CityDto cityDto) {
        City oldCity;
        if (!cityRepo.findById(cityId).isPresent()) {
            throw new ApiException(Exceptions.CITY_NOT_FOUND);
        }

        oldCity = cityRepo.findById(cityId).get();

        oldCity.setName(cityDto.getCountryName());
        oldCity.setSquare(cityDto.getSquare());
        return cityRepo.save(oldCity);
    }

    @Override
    public Country updateCountry(long countryId, CountryDto countryDto) {
        Country oldcountry;
        if (!countryRepo.findById(countryId).isPresent()) {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }

        oldcountry = countryRepo.findById(countryId).get();

        oldcountry.setName(countryDto.getName());
        return countryRepo.save(oldcountry);
    }

}
