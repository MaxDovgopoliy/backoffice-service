package com.service.backoffice.mapper;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.dto.TariffDtoForTripService;
import com.service.backoffice.model.Tariff;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TariffMapperUtil.class})
public abstract class TariffMapper {

    @Mapping(source = "cities", target = "cities", qualifiedByName = "namesFromCities")
    public abstract TariffDto toTariffDto(Tariff tariff);

    @Mapping(source = "cities", target = "cities", qualifiedByName = "citiesByNames")
    public abstract Tariff toTariff(TariffDto tariffDto);

    @Mapping(source = "cities", target = "cities", qualifiedByName = "namesFromCities")
    public abstract TariffDtoForTripService toTariffDtoForTripService(Tariff tariff);

    public abstract List<TariffDto> toTariffDtos(List<Tariff> tariffs);

    public abstract List<Tariff> toTariffs(List<TariffDto> tariffDtos);
}
