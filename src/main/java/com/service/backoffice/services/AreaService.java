package com.service.backoffice.services;

import com.service.backoffice.dto.AreaDto;
import java.util.List;

public interface AreaService {
    /**
     * @return List of Area DTOs
     */
    List<AreaDto> getAllAreas();

    boolean deleteArea(long id);

    AreaDto saveArea(AreaDto areaDto);

    AreaDto updateArea(long areaId, AreaDto newAreaDto);

    AreaDto getAreaById(long areaId);
}
