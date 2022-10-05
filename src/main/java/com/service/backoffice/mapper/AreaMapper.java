package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.model.Area;
import com.service.backoffice.repositories.CountryRepo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CountryRepo.class)
public interface AreaMapper {
    AreaMapper MAPPER = Mappers.getMapper(AreaMapper.class);

    default AreaDto toAreaDto(Area area) {
        if (area == null) {
            return null;
        }
        AreaDto areaDto = new AreaDto();
        areaDto.setCountryName(area.getCity().getCountry().getName());
        areaDto.setCityName(area.getCity().getName());
        areaDto.setCoordinatesDtoList(CoordinatesMapper
                .MAPPER.toListOfCoordinatesDto(area.getCoordinates()));

        return areaDto;
    }

    List<AreaDto> toAreaDtos(List<Area> areas);

}
