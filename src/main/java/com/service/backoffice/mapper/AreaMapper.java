package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.model.Area;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CoordinatesMapper.class)
public interface AreaMapper {
    AreaMapper MAPPER = Mappers.getMapper(AreaMapper.class);

    default AreaDto toAreaDto(Area area) {
        if (area == null) {
            return null;
        }
        AreaDto areaDto = new AreaDto();
        areaDto.setCountry(area.getCountry());
        areaDto.setCity(area.getCity());
        areaDto.setCoordinates(CoordinatesMapper.MAPPER
                        .toCoordinatesDtos(area.getListOfCoordinates()));
        return areaDto;
    }

    default Area toArea(AreaDto areaDto) {
        if (areaDto == null) {
            return null;
        }
        Area area = new Area();
        area.setCountry(areaDto.getCountry());
        area.setCity(areaDto.getCity());
        area.setListOfCoordinates(CoordinatesMapper.MAPPER
                .toListOfCoordinates(areaDto.getCoordinates()));
        return area;
    }

    List<AreaDto> toAreaDtos(List<Area> areas);

}
