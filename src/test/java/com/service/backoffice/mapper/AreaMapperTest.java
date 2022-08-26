package com.service.backoffice.mapper;

import org.mapstruct.factory.Mappers;

class AreaMapperTest {
AreaMapper mapper= Mappers.getMapper(AreaMapper.class);
//    List<Coordinates> listOfCoordinates= List.of(
//            new Coordinates(1L,123.32,123.43),
//            new Coordinates(2L,324.34,647.43),
//            new Coordinates(3L,235.43,283.4)
//    );
//    List<CoordinatesDto> listOfCoordinatesDto = List.of(
//            new CoordinatesDto(123.32,123.43),
//            new CoordinatesDto(324.34,647.43),
//            new CoordinatesDto(235.43,283.4)
//    );
//    List<Area> areas= List.of(
//            new Area(1L,"Ukraine","Lviv", List.copyOf(listOfCoordinates)),
//            new Area(2L,"Ukraine","Kyiv", List.copyOf(listOfCoordinates))
//    );
//
//    @Test
//    void toAreaDTO() {
//
//
//        Area area = new Area(1L,"Ukraine","Lviv", List.copyOf(listOfCoordinates));
//        AreaDto areaDTO= mapper.toAreaDto(area);
//
//        assertEquals(area.getCountry(),areaDTO.getCountry());
//        assertEquals(area.getCity(),areaDTO.getCity());
//        assertEquals(area.getListOfCoordinates(),CoordinatesMapper.MAPPER.toListOfCoordinates(areaDTO.getCoordinates()));
//    }
//
//    @Test
//    void toArea() {
//        AreaDto areaDTO =new AreaDto("Ukraine","Lviv", List.copyOf(listOfCoordinatesDto));
//        Area area =mapper.toArea(areaDTO);
//
//        assertEquals(area.getCountry(),areaDTO.getCountry());
//        assertEquals(area.getCity(),areaDTO.getCity());
//        assertEquals(area.getListOfCoordinates(),CoordinatesMapper.MAPPER.toListOfCoordinates(areaDTO.getCoordinates()));
//    }
//
//    @Test
//    void toAreaDTOs() {
//        List<AreaDto> areaDtos = mapper.toAreaDtos(areas);
//
//        assertEquals(areaDtos.get(0).getCity(),areas.get(0).getCity());
//        assertEquals(areaDtos.get(1).getCountry(),areas.get(1).getCountry());
//        assertEquals(CoordinatesMapper.MAPPER.toListOfCoordinates(areaDtos.get(1).getCoordinates()),
//                areas.get(1).getListOfCoordinates());
//
//    }

}