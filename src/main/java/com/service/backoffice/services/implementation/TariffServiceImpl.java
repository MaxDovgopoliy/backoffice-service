package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.exeption.ApiException;
import com.service.backoffice.exeption.Exceptions;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    TariffRepo tariffRepo;

    @Override
    public List<TariffDTO> getAllTariffs() {
        List<Tariff> tariffs = new ArrayList<Tariff>();
        tariffs.addAll(tariffRepo.findAll());
        List<TariffDTO>tariffDTOS= TariffMapper.MAPPER.toTariffDTOs(tariffs);
        return tariffDTOS;
    }

    @Override
    public TariffDTO saveTariff(String name, String description, String carType, int ratePerHour) {
        Tariff tariff = new Tariff(name, description, carType, ratePerHour);
        return TariffMapper.MAPPER.toTariffDTO(tariffRepo.save(tariff));
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
    public TariffDTO getTariffById(long id) {
        Optional<Tariff> foundTariff = tariffRepo.findById(id);
        if (foundTariff.isPresent()) {
            return TariffMapper.MAPPER.toTariffDTO(foundTariff.get());
        }
        throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
    }

    @Override
    public TariffDTO updateTariff(long tariffId, Tariff newTariff) {

        Tariff oldTariff;
        if(!tariffRepo.findById(tariffId).isPresent()){
           throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
        }

        oldTariff=tariffRepo.findById(tariffId).get();

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
        TariffDTO tariffDTO= TariffMapper.MAPPER.toTariffDTO(tariffRepo.save(oldTariff));
        return tariffDTO;
    }
}
