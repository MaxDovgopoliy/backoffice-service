package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDTO;
import com.service.backoffice.model.Coordinates;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface CoordinatesMapper {
    CoordinatesMapper MAPPER = Mappers.getMapper(CoordinatesMapper.class);

    CoordinatesDTO toCoordinatesDTO(Coordinates coordinates);

    Coordinates toCoordinates(CoordinatesDTO coordinatesDTO);

    List<CoordinatesDTO> toCoordinatesDTOs(List<Coordinates> coordinates);

    List<Coordinates> toCoordinates( List<CoordinatesDTO> coordinatesDTOs);
}
