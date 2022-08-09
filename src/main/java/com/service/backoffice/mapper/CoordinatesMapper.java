package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDTO;
import com.service.backoffice.model.Coordinates;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface CoordinatesMapper {
    CoordinatesMapper MAPPER = Mappers.getMapper(CoordinatesMapper.class);

    CoordinatesDTO toCoordinatesDTO(Coordinates coordinates);

    Coordinates toCoordinates(CoordinatesDTO coordinatesDTO);

    List<CoordinatesDTO> toCoordinatesDTOs(List<Coordinates> coordinates);

    List<Coordinates> toListOfCoordinates(List<CoordinatesDTO> coordinatesDTOs);
}
