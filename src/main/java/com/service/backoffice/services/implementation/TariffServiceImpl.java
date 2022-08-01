package com.service.backoffice.services.implementation;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    TariffRepo tariffRepo;

    @Override
    public Iterable<Tariff> getAllTariffs() {
        Iterable<Tariff> tariffs = tariffRepo.findAll();
        return tariffs;
    }
}
