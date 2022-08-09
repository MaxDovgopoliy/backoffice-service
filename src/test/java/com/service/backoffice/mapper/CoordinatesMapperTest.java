package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDTO;
import com.service.backoffice.model.Coordinates;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesMapperTest {
    CoordinatesMapper mapper= Mappers.getMapper(CoordinatesMapper.class);
    private List<Coordinates> listOfCoordinates = List.of(
            new Coordinates(1L,123.23,432.432),
            new Coordinates(1L,123.23,432.432)
    );
    private List<CoordinatesDTO> coordinatesDTOS = List.of(
            new CoordinatesDTO(123.23,432.432),
            new CoordinatesDTO(123.23,432.432)
    );

    @Test
    void toCoordinatesDTO() {
        CoordinatesDTO coordinatesDTO=mapper.toCoordinatesDTO(listOfCoordinates.get(0));

        assertEquals(coordinatesDTO.getLatitude(), listOfCoordinates.get(0).getLatitude());
        assertEquals(coordinatesDTO.getLongitude(), listOfCoordinates.get(0).getLongitude());
    }

    @Test
    void toCoordinates() {
        Coordinates coordinates=mapper.toCoordinates(coordinatesDTOS.get(1));

        assertEquals(coordinates.getLatitude(),coordinatesDTOS.get(1).getLatitude());
        assertEquals(coordinates.getLongitude(),coordinatesDTOS.get(1).getLongitude());
    }

    @Test
    void toCoordinatesDTOs() {
        List<CoordinatesDTO> coordinatesDTOList =mapper.toCoordinatesDTOs(listOfCoordinates);

        assertEquals(coordinatesDTOList.get(0).getLatitude(),listOfCoordinates.get(0).getLatitude());
        assertEquals(coordinatesDTOList.get(0).getLongitude(),listOfCoordinates.get(0).getLongitude());

        assertEquals(coordinatesDTOList.get(1).getLatitude(),listOfCoordinates.get(1).getLatitude());
        assertEquals(coordinatesDTOList.get(1).getLongitude(),listOfCoordinates.get(1).getLongitude());
    }

    @Test
    void toListOfCoordinates() {
        List<Coordinates> coordinatesList =mapper.toListOfCoordinates(coordinatesDTOS);

        assertEquals(coordinatesList.get(0).getLatitude(),coordinatesDTOS.get(0).getLatitude());
        assertEquals(coordinatesList.get(0).getLongitude(),coordinatesDTOS.get(0).getLongitude());

        assertEquals(coordinatesList.get(1).getLatitude(),coordinatesDTOS.get(1).getLatitude());
        assertEquals(coordinatesList.get(1).getLongitude(),coordinatesDTOS.get(1).getLongitude());

    }
}