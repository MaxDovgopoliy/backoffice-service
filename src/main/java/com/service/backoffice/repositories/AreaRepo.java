package com.service.backoffice.repositories;

import com.service.backoffice.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepo extends JpaRepository<Area, Long> {
    Optional<Area> findByCountry(String country);
}
