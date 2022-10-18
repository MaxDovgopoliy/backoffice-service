package com.service.backoffice.mapper;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapperUtil {
    private final TariffRepo tariffRepo;

    @Named("carTypeByTariffId")
    String carTypeByRatePerHour(int value) {
        return tariffRepo.findById((long) value).get().getCarType();
    }

    @Named("tariffById")
    Tariff tariffById(long value) {
        return tariffRepo.findById(value).get();
    }

    @Named("idFromTariff")
    long idFromTariff(Tariff tariff) {
        return tariff.getId();
    }
}
