package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.model.Area;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-18T13:00:35+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class AreaMapperImpl implements AreaMapper {

    @Override
    public List<AreaDto> toAreaDtos(List<Area> areas) {
        if ( areas == null ) {
            return null;
        }

        List<AreaDto> list = new ArrayList<AreaDto>( areas.size() );
        for ( Area area : areas ) {
            list.add( toAreaDto( area ) );
        }

        return list;
    }
}
