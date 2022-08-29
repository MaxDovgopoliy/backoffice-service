package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffRepo tariffRepo;

    @Override
    public List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = tariffRepo.findAll();
        return tariffs;
    }

    @Override
    public Tariff saveTariff(TariffDto tariffDto) {
        Tariff tariff = new Tariff(tariffDto.getName(),
                tariffDto.getDescription(),
                tariffDto.getCarType(),
                tariffDto.getRatePerHour());
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
}
