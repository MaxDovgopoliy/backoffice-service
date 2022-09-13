package com.service.backoffice.services;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import java.util.List;

public interface TariffService {

    List<Tariff> getAllTariffs();

    Tariff saveTariff(TariffDto tariffDto);

    boolean deleteTariff(long tariffId);

    Tariff getTariffById(long id);

    Tariff updateTariff(long tariffId, Tariff newTariff);

    Tariff getTariffByCarType(String carType);
}
