package com.service.backoffice.mapper;

import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.model.Country;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {
    CountryMapper MAPPER = Mappers.getMapper(CountryMapper.class);

    List<CountryDto> toCountryDtos(List<Country> countries);

    CountryDto toCountryDto(Country country);

    Country toCountry(CountryDto countryDto);
}
