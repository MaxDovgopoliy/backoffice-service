package com.service.backoffice.mapper;

import com.service.backoffice.dto.CityDto;
import com.service.backoffice.model.City;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CoordinatesMapper.class)
public interface CityMapper {
    CityMapper MAPPER = Mappers.getMapper(CityMapper.class);

    List<CityDto> toCityDtos(List<City> cities);

    default CityDto toCityDto(City city) {
        if (city == null) {
            return null;
        }
        CityDto cityDto = new CityDto();
        cityDto.setCountryName(city.getCountry().getName());
        cityDto.setName(city.getName());
        cityDto.setCoordinatesDtoList(CoordinatesMapper.MAPPER
                .toListOfCoordinatesDto(city.getCoordinates()));
        cityDto.setCoefficientForTariff(city.getCoefficientForTariff());

        return cityDto;
    }

    City toCity(CityDto cityDto);
}
