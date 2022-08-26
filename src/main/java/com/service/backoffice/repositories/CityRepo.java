package com.service.backoffice.repositories;

import com.service.backoffice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {
    City findByNameIgnoreCase(String cityName);
}
