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
        Tariff tariff =new Tariff(name,description,carType,ratePerHour);
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
        if(foundTariff.isPresent()){
            return foundTariff.get();
        }
        throw new RuntimeException("no such tariff");
    }
}
