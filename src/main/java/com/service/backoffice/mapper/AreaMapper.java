package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Tariff;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface AreaMapper {
    AreaMapper MAPPER = Mappers.getMapper(AreaMapper.class);

    AreaDTO toAreaDTO(Area area);

    Area toArea(AreaDTO areaDTO);

    List<AreaDTO> toAreaDTOs(List<Area> areas);

    List<Area> toAreas( List<AreaDTO> areaDTOs);
}
