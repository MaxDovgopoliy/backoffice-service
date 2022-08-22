package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.TariffMapper;
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
    public List<TariffDto> getAllTariffs() {
        List<Tariff> tariffs = tariffRepo.findAll();
        List<TariffDto> tariffDtos = TariffMapper.MAPPER.toTariffDtos(tariffs);
        return tariffDtos;
    }

    @Override
    public TariffDto saveTariff(TariffDto tariffDto) {
        Tariff tariff = new Tariff(tariffDto.getName(),
                                   tariffDto.getDescription(),
                                   tariffDto.getCarType(),
                                   tariffDto.getRatePerHour());
        return TariffMapper.MAPPER.toTariffDto(tariffRepo.save(tariff));
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
    public TariffDto getTariffById(long id) {
        Optional<Tariff> foundTariff = tariffRepo.findById(id);
        if (foundTariff.isPresent()) {
            return TariffMapper.MAPPER.toTariffDto(foundTariff.get());
        }
        throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
    }

    @Override
    public TariffDto updateTariff(long tariffId, Tariff newTariff) {

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
        TariffDto tariffDto = TariffMapper.MAPPER.toTariffDto(tariffRepo.save(oldTariff));
        return tariffDto;
    }
}
