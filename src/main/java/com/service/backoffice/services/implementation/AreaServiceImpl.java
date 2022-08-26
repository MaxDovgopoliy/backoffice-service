package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.services.AreaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepo areaRepo;
    @Autowired
    private CountryRepo countryRepo;

    @Override
    public List<Area> getAllAreas() {
        return areaRepo.findAll();
    }

    @Override
    public boolean deleteArea(long id) {
        try {
            areaRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Area saveArea(AreaDto areaDto) {
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
                Area area = new Area(areaDto.getSquare(), areaDto.getAddress(), city);
                return areaRepo.save(area);
            } else {
                throw new ApiException(Exceptions.AREA_TOO_BIG);
            }
        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }
    }

    @Override
    public Area updateArea(long areaId, AreaDto newAreaDto) {
        Area oldArea;
        if (!areaRepo.findById(areaId).isPresent()) {
            throw new ApiException(Exceptions.AREA_NOT_FOUND);
        }
        oldArea = areaRepo.findById(areaId).get();

        Country country = countryRepo.findByNameIgnoreCase(newAreaDto.getCountryName());
        if (country != null) {
            City city = country
                    .getCities()
                    .stream()
                    .filter(i -> i.getName().equalsIgnoreCase(newAreaDto.getCityName()))
                    .findFirst()
                    .orElseThrow(() -> new ApiException(Exceptions.CITY_NOT_FOUND));

            double allAreasInCitySquare = city
                    .getAreas()
                    .stream()
                    .mapToDouble(Area::getSquare)
                    .sum();

            if (city.getSquare() >= allAreasInCitySquare + newAreaDto.getSquare()) {
                oldArea.setAddress(newAreaDto.getAddress());
                oldArea.setCity(city);
                oldArea.setSquare(newAreaDto.getSquare());
                return areaRepo.save(oldArea);
            } else {
                throw new ApiException(Exceptions.AREA_TOO_BIG);
            }

        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }

    }

    @Override
    public Area getAreaById(long areaId) {
        Optional<Area> foundArea = areaRepo.findById(areaId);
        if (foundArea.isPresent()) {
            return foundArea.get();
        }
        throw new ApiException(Exceptions.AREA_NOT_FOUND);
    }
}
