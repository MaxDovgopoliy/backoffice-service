package com.service.backoffice.services;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import java.util.List;

public interface TariffService {

    List<TariffDto> getAllTariffs();

    TariffDto saveTariff(TariffDto tariffDto);

    boolean deleteTariff(long tariffId);

    TariffDto getTariffById(long id);

    TariffDto updateTariff(long tariffId, Tariff newTariff);
}
