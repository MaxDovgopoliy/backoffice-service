package com.service.backoffice.repositories;

import com.service.backoffice.model.Area;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepo extends JpaRepository<Area, Long> {
    Optional<Area> findByCountry(String country);
}
