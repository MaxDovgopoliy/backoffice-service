package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.model.Area;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CoordinatesMapper.class)
public interface AreaMapper {
    AreaMapper MAPPER = Mappers.getMapper(AreaMapper.class);

    default AreaDTO toAreaDTO(Area area) {
        if (area == null) {
            return null;
        }
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setCountry(area.getCountry());
        areaDTO.setCity(area.getCity());
        areaDTO.setCoordinatesDTOList(CoordinatesMapper.MAPPER.toCoordinatesDTOs(area.getListOfCoordinates()));
        return areaDTO;
    }

    default Area toArea(AreaDTO areaDTO){
        if (areaDTO == null) {
            return null;
        }
        Area area = new Area();
        area.setCountry(areaDTO.getCountry());
        area.setCity(areaDTO.getCity());
        area.setListOfCoordinates(CoordinatesMapper.MAPPER.toListOfCoordinates(areaDTO.getCoordinatesDTOList()));
        return area;
    }

    List<AreaDTO> toAreaDTOs(List<Area> areas);


}
