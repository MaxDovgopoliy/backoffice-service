package com.service.backoffice.mapper;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TariffMapper {

    TariffMapper MAPPER = Mappers.getMapper(TariffMapper.class);

    TariffDto toTariffDto(Tariff tariff);

    Tariff toTariff(TariffDto tariffDto);

    List<TariffDto> toTariffDtos(List<Tariff> tariffs);

}
