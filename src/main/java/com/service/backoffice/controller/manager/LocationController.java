package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CountryMapper;
import com.service.backoffice.services.LocationService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/manager")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/cities")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CityMapper.MAPPER.toCityDto(locationService.saveCity(cityDto)));
    }

    @PostMapping("/countries")
    public ResponseEntity<CountryDto> addCountry(@RequestBody @Valid CountryDto countryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CountryMapper.MAPPER.toCountryDto(locationService.saveCountry(countryDto)));
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Boolean> deleteCountry(@PathVariable("id") long countryId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                locationService.deleteCountryById(countryId));
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Boolean> deleteCity(@PathVariable("id") long cityId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                locationService.deleteCityById(cityId));
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable("id") long cityId,
                                              @RequestBody @Valid CityDto cityDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CityMapper.MAPPER.toCityDto(locationService.updateCity(cityId, cityDto)));
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable("id") long countryId,
                                                    @RequestBody @Valid CountryDto countryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CountryMapper.MAPPER.toCountryDto(
                        locationService.updateCountry(countryId, countryDto)));
    }
}
