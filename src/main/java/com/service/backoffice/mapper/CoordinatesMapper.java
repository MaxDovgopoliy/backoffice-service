package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Coordinates;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoordinatesMapper {
    CoordinatesMapper MAPPER = Mappers.getMapper(CoordinatesMapper.class);

    Coordinates toCoordinates(CoordinatesDto coordinatesDto);

    List<Coordinates> toListOfCoordinates(List<CoordinatesDto> coordinatesDtos);

    List<CoordinatesDto> toListOfCoordinatesDto(List<Coordinates> coordinates);

    CoordinatesDto toCoordinatesDto(Coordinates coordinates);
}
