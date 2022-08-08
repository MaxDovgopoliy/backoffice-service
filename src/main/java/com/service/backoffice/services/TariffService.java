package com.service.backoffice.services;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Tariff;

import java.util.List;

public interface TariffService {

    List<TariffDTO> getAllTariffs();

   void saveTariff(String name, String description, String carType, int ratePerHour);

   boolean deleteTariff(long tariffId);

   TariffDTO getTariffById(long id);

   TariffDTO updateTariff(long tariffId, Tariff newTariff);
}
