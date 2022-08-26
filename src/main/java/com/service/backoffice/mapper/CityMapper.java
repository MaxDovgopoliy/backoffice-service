package com.service.backoffice.mapper;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.model.City;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {
    CityMapper MAPPER = Mappers.getMapper(CityMapper.class);

    List<CityDto> toCityDtos(List<City> cities);

    default CityDto toCityDto(City city) {
        if (city == null) {
            return null;
        }
        CityDto cityDto = new CityDto();
        cityDto.setCountryName(city.getCountry().getName());
        cityDto.setSquare(city.getSquare());
        cityDto.setName(city.getName());

        return cityDto;
    }

    City toCity(CityDto cityDto);
}
