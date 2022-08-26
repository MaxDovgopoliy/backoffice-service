package com.service.backoffice.repositories;

import com.service.backoffice.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {
    Country findByNameIgnoreCase(String name);
}
