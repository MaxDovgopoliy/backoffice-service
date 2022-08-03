package com.service.backoffice.services.implementation;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    TariffRepo tariffRepo;

    @Override
    public Iterable<Tariff> getAllTariffs() {
        Iterable<Tariff> tariffs = tariffRepo.findAll();
        return tariffs;
    }

    @Override
    public void saveTariff(String name, String description, String carType, int ratePerHour) {
        Tariff tariff = new Tariff(name, description, carType, ratePerHour);
        tariffRepo.save(tariff);
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
        throw new RuntimeException("no such tariff");
    }

    @Override
    public Tariff updateTariff(long tariffId, Tariff newTariff) {
        Tariff oldTariff = tariffRepo.findById(tariffId).get();
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
}
