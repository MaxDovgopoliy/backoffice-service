package com.service.backoffice.services;

import com.service.backoffice.dto.AreaDTO;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface AreaService {

     List<AreaDTO> getAllAreas();
     boolean deleteArea(long id);
     AreaDTO saveArea(AreaDTO areaDTO);
}
