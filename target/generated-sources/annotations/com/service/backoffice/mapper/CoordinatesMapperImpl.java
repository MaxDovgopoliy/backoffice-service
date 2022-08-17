package com.service.backoffice.mapper;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.Coordinates;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-16T16:11:16+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class CoordinatesMapperImpl implements CoordinatesMapper {

    @Override
    public CoordinatesDto toCoordinatesDto(Coordinates coordinates) {
        if ( coordinates == null ) {
            return null;
        }

        CoordinatesDto coordinatesDto = new CoordinatesDto();

        coordinatesDto.setLatitude( coordinates.getLatitude() );
        coordinatesDto.setLongitude( coordinates.getLongitude() );

        return coordinatesDto;
    }

    @Override
    public Coordinates toCoordinates(CoordinatesDto coordinatesDto) {
        if ( coordinatesDto == null ) {
            return null;
        }

        Coordinates coordinates = new Coordinates();

        coordinates.setLatitude( coordinatesDto.getLatitude() );
        coordinates.setLongitude( coordinatesDto.getLongitude() );

        return coordinates;
    }

    @Override
    public List<CoordinatesDto> toCoordinatesDtos(List<Coordinates> coordinates) {
        if ( coordinates == null ) {
            return null;
        }

        List<CoordinatesDto> list = new ArrayList<CoordinatesDto>( coordinates.size() );
        for ( Coordinates coordinates1 : coordinates ) {
            list.add( toCoordinatesDto( coordinates1 ) );
        }

        return list;
    }

    @Override
    public List<Coordinates> toListOfCoordinates(List<CoordinatesDto> coordinatesDtos) {
        if ( coordinatesDtos == null ) {
            return null;
        }

        List<Coordinates> list = new ArrayList<Coordinates>( coordinatesDtos.size() );
        for ( CoordinatesDto coordinatesDto : coordinatesDtos ) {
            list.add( toCoordinates( coordinatesDto ) );
        }

        return list;
    }
}
