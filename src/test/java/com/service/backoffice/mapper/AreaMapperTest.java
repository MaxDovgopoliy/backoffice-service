package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.dto.CoordinatesDTO;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Coordinates;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaMapperTest {
AreaMapper mapper= Mappers.getMapper(AreaMapper.class);
    List<Coordinates> listOfCoordinates= List.of(
            new Coordinates(1L,123.32,123.43),
            new Coordinates(2L,324.34,647.43),
            new Coordinates(3L,235.43,283.4)
    );
    List<CoordinatesDTO> listOfCoordinatesDTO= List.of(
            new CoordinatesDTO(123.32,123.43),
            new CoordinatesDTO(324.34,647.43),
            new CoordinatesDTO(235.43,283.4)
    );
    List<Area> areas= List.of(
            new Area(1L,"Ukraine","Lviv", List.copyOf(listOfCoordinates)),
            new Area(2L,"Ukraine","Kyiv", List.copyOf(listOfCoordinates))
    );

    @Test
    void toAreaDTO() {


        Area area = new Area(1L,"Ukraine","Lviv", List.copyOf(listOfCoordinates));
        AreaDTO areaDTO= mapper.toAreaDTO(area);

        assertEquals(area.getCountry(),areaDTO.getCountry());
        assertEquals(area.getCity(),areaDTO.getCity());
        assertEquals(area.getListOfCoordinates(),CoordinatesMapper.MAPPER.toCoordinates(areaDTO.getCoordinatesDTOList()));
    }

    @Test
    void toArea() {
        AreaDTO areaDTO =new AreaDTO("Ukraine","Lviv", List.copyOf(listOfCoordinatesDTO));
        Area area =mapper.toArea(areaDTO);

        assertEquals(area.getCountry(),areaDTO.getCountry());
        assertEquals(area.getCity(),areaDTO.getCity());
        assertEquals(area.getListOfCoordinates(),CoordinatesMapper.MAPPER.toCoordinates(areaDTO.getCoordinatesDTOList()));
    }

    @Test
    void toAreaDTOs() {
        List<AreaDTO> areaDTOs = mapper.toAreaDTOs(areas);

        assertEquals(areaDTOs.get(0).getCity(),areas.get(0).getCity());
        assertEquals(areaDTOs.get(1).getCountry(),areas.get(1).getCountry());
        assertEquals(CoordinatesMapper.MAPPER.toCoordinates(areaDTOs.get(1).getCoordinatesDTOList()),
                areas.get(1).getListOfCoordinates());

    }

}