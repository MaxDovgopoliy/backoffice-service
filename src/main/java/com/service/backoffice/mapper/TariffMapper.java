package com.service.backoffice.mapper;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface TariffMapper {

    TariffMapper MAPPER = Mappers.getMapper(TariffMapper.class);

    TariffDTO toTariffDTO(Tariff tariff);

    Tariff toTariff(TariffDTO tariffDTO);

    List<TariffDTO> toTariffDTOs(List<Tariff> tariffs);

}
