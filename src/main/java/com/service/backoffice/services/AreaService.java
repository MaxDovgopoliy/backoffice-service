package com.service.backoffice.services;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.model.Area;
import java.util.List;

public interface AreaService {
    /**
     * @return List of Area DTOs
     */
    List<Area> getAllAreas();

    boolean deleteArea(long id);

    Area saveArea(AreaDto areaDto);

    Area updateArea(long areaId, AreaDto newAreaDto);

    Area getAreaById(long areaId);
}
