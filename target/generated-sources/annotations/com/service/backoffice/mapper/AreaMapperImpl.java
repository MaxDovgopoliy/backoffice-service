package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.model.Area;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T12:54:41+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class AreaMapperImpl implements AreaMapper {

    @Override
    public List<AreaDTO> toAreaDTOs(List<Area> areas) {
        if ( areas == null ) {
            return null;
        }

        List<AreaDTO> list = new ArrayList<AreaDTO>( areas.size() );
        for ( Area area : areas ) {
            list.add( toAreaDTO( area ) );
        }

        return list;
    }
}
