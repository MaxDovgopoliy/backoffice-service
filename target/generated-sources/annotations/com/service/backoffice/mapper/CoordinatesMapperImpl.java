package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDTO;
import com.service.backoffice.model.Coordinates;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T12:54:40+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class CoordinatesMapperImpl implements CoordinatesMapper {

    @Override
    public CoordinatesDTO toCoordinatesDTO(Coordinates coordinates) {
        if ( coordinates == null ) {
            return null;
        }

        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();

        coordinatesDTO.setLatitude( coordinates.getLatitude() );
        coordinatesDTO.setLongitude( coordinates.getLongitude() );

        return coordinatesDTO;
    }

    @Override
    public Coordinates toCoordinates(CoordinatesDTO coordinatesDTO) {
        if ( coordinatesDTO == null ) {
            return null;
        }

        Coordinates coordinates = new Coordinates();

        coordinates.setLatitude( coordinatesDTO.getLatitude() );
        coordinates.setLongitude( coordinatesDTO.getLongitude() );

        return coordinates;
    }

    @Override
    public List<CoordinatesDTO> toCoordinatesDTOs(List<Coordinates> coordinates) {
        if ( coordinates == null ) {
            return null;
        }

        List<CoordinatesDTO> list = new ArrayList<CoordinatesDTO>( coordinates.size() );
        for ( Coordinates coordinates1 : coordinates ) {
            list.add( toCoordinatesDTO( coordinates1 ) );
        }

        return list;
    }

    @Override
    public List<Coordinates> toListOfCoordinates(List<CoordinatesDTO> coordinatesDTOs) {
        if ( coordinatesDTOs == null ) {
            return null;
        }

        List<Coordinates> list = new ArrayList<Coordinates>( coordinatesDTOs.size() );
        for ( CoordinatesDTO coordinatesDTO : coordinatesDTOs ) {
            list.add( toCoordinates( coordinatesDTO ) );
        }

        return list;
    }
}
