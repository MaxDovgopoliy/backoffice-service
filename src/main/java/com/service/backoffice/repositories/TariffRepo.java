package com.service.backoffice.repositories;

import com.service.backoffice.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepo extends JpaRepository<Tariff,Long> {
}
