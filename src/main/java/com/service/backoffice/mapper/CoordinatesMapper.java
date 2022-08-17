package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Coordinates;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoordinatesMapper {
    CoordinatesMapper MAPPER = Mappers.getMapper(CoordinatesMapper.class);

    CoordinatesDto toCoordinatesDto(Coordinates coordinates);

    Coordinates toCoordinates(CoordinatesDto coordinatesDto);

    List<CoordinatesDto> toCoordinatesDtos(List<Coordinates> coordinates);

    List<Coordinates> toListOfCoordinates(List<CoordinatesDto> coordinatesDtos);
}
