package com.service.backoffice.services;

import com.service.backoffice.model.Tariff;

public interface TariffService {

   public Iterable<Tariff> getAllTariffs();

   void saveTariff(String name, String description, String carType, int ratePerHour);

   boolean deleteTariff(long tariffId);

   Tariff getTariffById(long id);
}
