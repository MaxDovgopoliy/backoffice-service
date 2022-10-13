package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import com.service.backoffice.util.CityUtil;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TariffServiceImpl implements TariffService {
    private TariffRepo tariffRepo;
    private CityRepo cityRepo;
    private TariffMapper tariffMapper;

    public TariffServiceImpl(TariffRepo tariffRepo, CityRepo cityRepo,
                             TariffMapper tariffMapper) {
        this.tariffRepo = tariffRepo;
        this.cityRepo = cityRepo;
        this.tariffMapper = tariffMapper;
    }

    @Override
    public List<Tariff> getAllTariffs(String cityName) {
        City city = cityRepo.findByNameIgnoreCase(cityName);
        if (city != null) {
            List<Tariff> tariffs = tariffRepo.findAll();
            tariffs.forEach(tariff -> tariff.setRatePerHour(
                    tariff.getRatePerHour() * city.getCoefficientForTariff()));

            return tariffs;
        } else {
            throw new ApiException(Exceptions.CITY_NOT_FOUND);
        }
    }

    @Override
    public Tariff saveTariff(TariffDto tariffDto) {
        Tariff tariff = tariffMapper.toTariff(tariffDto);
        return tariffRepo.save(tariff);
    }

    @Override
    public boolean deleteTariff(long tariffId) {
        try {
            tariffRepo.deleteById(tariffId);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Tariff getTariffById(long id) {
        Optional<Tariff> foundTariff = tariffRepo.findById(id);
        if (foundTariff.isPresent()) {
            return foundTariff.get();
        }
        throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
    }

    @Override
    public Tariff updateTariff(long tariffId, Tariff newTariff) {

        Tariff oldTariff;
        if (!tariffRepo.findById(tariffId).isPresent()) {
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
        }

        oldTariff = tariffRepo.findById(tariffId).get();

        if (newTariff.getName() != null) {
            oldTariff.setName(newTariff.getName());
        }
        if (newTariff.getDescription() != null) {
            oldTariff.setDescription(newTariff.getDescription());
        }
        if (newTariff.getCarType() != null) {
            oldTariff.setCarType(newTariff.getCarType());
        }
        if (newTariff.getRatePerHour() != 0) {
            oldTariff.setRatePerHour(newTariff.getRatePerHour());
        }
        return tariffRepo.save(oldTariff);
    }

    @Override
    public Tariff getTariffForCityAndCarType(String carType, double latitude, double longitude) {
        Tariff tariff = tariffRepo.findByCarTypeIgnoreCase(carType);
        if (tariff == null) {
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
        }
        City cityByCoordinates = CityUtil.findCityByCoordinates(latitude, longitude);
        if (tariff.getCities().contains(cityByCoordinates)) {
            tariff.setRatePerHour(
                    tariff.getRatePerHour() * cityByCoordinates.getCoefficientForTariff());
            return tariff;
        } else {
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND_FOR_CITY);
        }
    }

}
