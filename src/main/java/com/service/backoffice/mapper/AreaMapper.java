package com.service.backoffice.mapper;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CountryRepo;
import java.util.List;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CountryRepo.class)
public interface AreaMapper {
    AreaMapper MAPPER = Mappers.getMapper(AreaMapper.class);

    default AreaDto toAreaDto(Area area) {
        if (area == null) {
            return null;
        }
        AreaDto areaDto = new AreaDto();
        areaDto.setCountryName(area.getCity().getCountry().getName());
        areaDto.setCityName(area.getCity().getName());
        areaDto.setAddress(AddressMapper.MAPPER.toAddressDto(area.getAddress()));
        areaDto.setSquare(area.getSquare());

        return areaDto;
    }

    default Area toArea(AreaDto areaDto, @Context CountryRepo countryRepo) {
        if (areaDto == null) {
            return null;
        }
        Area area = new Area();
        Country country = countryRepo.findByNameIgnoreCase(areaDto.getCountryName());
        if (country != null) {
            area.setCity(country
                    .getCities()
                    .stream()
                    .filter(c -> c.getName()
                            .equals(areaDto.getCityName()))
                    .findFirst()
                    .orElseThrow(() -> new ApiException(Exceptions.CITY_NOT_FOUND)));
        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }

        return area;
    }

    List<AreaDto> toAreaDtos(List<Area> areas);

}
