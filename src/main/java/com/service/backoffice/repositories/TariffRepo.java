package com.service.backoffice.repositories;

import com.service.backoffice.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepo extends JpaRepository<Tariff,Long> {
    Tariff findByCarTypeIgnoreCase(String name);

}
