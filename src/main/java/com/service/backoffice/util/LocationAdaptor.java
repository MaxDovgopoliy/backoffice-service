package com.service.backoffice.util;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CityDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.AddressMapper;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.model.Address;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationAdaptor {
    @Autowired
    private CountryRepo countryRepo;

    public City makeCityFromDto(CityDto cityDto) {
        Country country = countryRepo.findByNameIgnoreCase(cityDto.getCountryName());
        if (country != null) {
            City cityFromDb = country.getCities()
                    .stream()
                    .filter(i -> i.getName().equalsIgnoreCase(cityDto.getName()))
                    .findFirst()
                    .orElse(null);
            if (cityFromDb == null) {
                City city = CityMapper.MAPPER.toCity(cityDto);
                city.setCountry(country);
                return city;
            } else {
                throw new ApiException(Exceptions.CITY_ALREADY_EXIST);
            }
        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }
    }

    public Area makeAreaFromDto(Area area, AreaDto areaDto) {
        Country country = countryRepo.findByNameIgnoreCase(areaDto.getCountryName());
        if (country != null) {
            City city = country
                    .getCities()
                    .stream()
                    .filter(i -> i.getName().equalsIgnoreCase(areaDto.getCityName()))
                    .findFirst()
                    .orElseThrow(() -> new ApiException(Exceptions.CITY_NOT_FOUND));

            double allAreasInCitySquare = city
                    .getAreas()
                    .stream()
                    .mapToDouble(c -> c.getSquare())
                    .sum();

            if (city.getSquare() >= allAreasInCitySquare + areaDto.getSquare()) {
                area.setSquare(areaDto.getSquare());
                area.setCity(city);
                Address address = AddressMapper.MAPPER.toAddress(areaDto.getAddress());
                address.setArea(area);
                area.setAddress(address);
                return area;
            } else {
                throw new ApiException(Exceptions.AREA_TOO_BIG);
            }
        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }
    }
}
