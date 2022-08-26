package com.service.backoffice.controller.user;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CountryMapper;
import com.service.backoffice.services.LocationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.status(HttpStatus.OK).body(
                CountryMapper.MAPPER.toCountryDtos(locationService.getAllCountries()));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        return ResponseEntity.status(HttpStatus.OK).body(
                CityMapper.MAPPER.toCityDtos(locationService.getAllCities()));
    }

    @PostMapping("/cities")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CityMapper.MAPPER.toCityDto(locationService.saveCity(cityDto)));
    }

    @PostMapping("/countries")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                CountryMapper.MAPPER.toCountryDto(locationService.saveCountry(countryDto)));
    }

    @DeleteMapping("/countries")
    public ResponseEntity<Boolean> deleteCountry(@RequestParam long countryId) {
        return ResponseEntity.status(HttpStatus.OK).body(
               locationService.deleteCountryById(countryId));
    }
}
