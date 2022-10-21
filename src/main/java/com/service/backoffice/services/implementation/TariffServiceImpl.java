package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import com.service.backoffice.util.AreaUtil;
import com.service.backoffice.util.CurrencyUtil;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TariffServiceImpl implements TariffService {
    private TariffRepo tariffRepo;
    private CityRepo cityRepo;
    private CountryRepo countryRepo;
    private TariffMapper tariffMapper;

    public TariffServiceImpl(TariffRepo tariffRepo, CityRepo cityRepo,
                             CountryRepo countryRepo, TariffMapper tariffMapper) {
        this.tariffRepo = tariffRepo;
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
        this.tariffMapper = tariffMapper;
    }

    @Override
    public List<Tariff> getAllTariffs(String countryName, String cityName) {
        Country country = countryRepo.findByNameIgnoreCase(countryName);
        if (country != null) {
            City city = country.getCities()
                    .stream()
                    .filter(c -> c.getName().equalsIgnoreCase(cityName))
                    .findAny()
                    .orElse(null);
            if (city != null) {
                List<Tariff> tariffs = tariffRepo.findAll();
                for (Tariff tariff : tariffs) {
                    DecimalFormat df = new DecimalFormat("#.##");
                    tariff.setRatePerHour(
                            Double.parseDouble(df.format(
                                    CurrencyUtil.convertCurrencyRate(tariff.getCurrency(),
                                            country.getCurrency(), tariff.getRatePerHour())
                                            * city.getCoefficientForTariff())));

                    tariff.setCurrency(country.getCurrency());
                }
                return tariffs;
            } else {
                throw new ApiException(Exceptions.CITY_NOT_FOUND);
            }
        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
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

        City cityByCoordinates = AreaUtil.findCityByCoordinates(latitude, longitude);
        if (tariff.getCities().contains(cityByCoordinates)) {
            Country country = cityByCoordinates.getCountry();

            double ratePerHour = CurrencyUtil.convertCurrencyRate(tariff.getCurrency(),
                    country.getCurrency(), tariff.getRatePerHour())
                    * cityByCoordinates.getCoefficientForTariff();

            DecimalFormat df = new DecimalFormat("#.##");
            ratePerHour = Double.parseDouble(df.format(ratePerHour));

            tariff.setRatePerHour(ratePerHour);
            tariff.setCurrency(country.getCurrency());
            return tariff;
        } else {
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND_FOR_CITY);
        }
    }

}
