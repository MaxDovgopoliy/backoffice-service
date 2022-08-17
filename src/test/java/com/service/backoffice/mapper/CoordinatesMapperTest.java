package com.service.backoffice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Coordinates;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CoordinatesMapperTest {
    CoordinatesMapper mapper= Mappers.getMapper(CoordinatesMapper.class);
    private List<Coordinates> listOfCoordinates = List.of(
            new Coordinates(1L,123.23,432.432),
            new Coordinates(1L,123.23,432.432)
    );
    private List<CoordinatesDto> coordinatesDtos = List.of(
            new CoordinatesDto(123.23,432.432),
            new CoordinatesDto(123.23,432.432)
    );

    @Test
    void toCoordinatesDTO() {
        CoordinatesDto coordinatesDTO=mapper.toCoordinatesDto(listOfCoordinates.get(0));

        assertEquals(coordinatesDTO.getLatitude(), listOfCoordinates.get(0).getLatitude());
        assertEquals(coordinatesDTO.getLongitude(), listOfCoordinates.get(0).getLongitude());
    }

    @Test
    void toCoordinates() {
        Coordinates coordinates=mapper.toCoordinates(coordinatesDtos.get(1));

        assertEquals(coordinates.getLatitude(), coordinatesDtos.get(1).getLatitude());
        assertEquals(coordinates.getLongitude(), coordinatesDtos.get(1).getLongitude());
    }

    @Test
    void toCoordinatesDTOs() {
        List<CoordinatesDto> coordinatesDtoList =mapper.toCoordinatesDtos(listOfCoordinates);

        assertEquals(coordinatesDtoList.get(0).getLatitude(),listOfCoordinates.get(0).getLatitude());
        assertEquals(coordinatesDtoList.get(0).getLongitude(),listOfCoordinates.get(0).getLongitude());

        assertEquals(coordinatesDtoList.get(1).getLatitude(),listOfCoordinates.get(1).getLatitude());
        assertEquals(coordinatesDtoList.get(1).getLongitude(),listOfCoordinates.get(1).getLongitude());
    }

    @Test
    void toListOfCoordinates() {
        List<Coordinates> coordinatesList =mapper.toListOfCoordinates(coordinatesDtos);

        assertEquals(coordinatesList.get(0).getLatitude(), coordinatesDtos.get(0).getLatitude());
        assertEquals(coordinatesList.get(0).getLongitude(), coordinatesDtos.get(0).getLongitude());

        assertEquals(coordinatesList.get(1).getLatitude(), coordinatesDtos.get(1).getLatitude());
        assertEquals(coordinatesList.get(1).getLongitude(), coordinatesDtos.get(1).getLongitude());

    }
}