package com.service.backoffice.mapper;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-16T16:11:16+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class TariffMapperImpl implements TariffMapper {

    @Override
    public TariffDto toTariffDto(Tariff tariff) {
        if ( tariff == null ) {
            return null;
        }

        TariffDto tariffDto = new TariffDto();

        tariffDto.setName( tariff.getName() );
        tariffDto.setDescription( tariff.getDescription() );
        tariffDto.setCarType( tariff.getCarType() );
        tariffDto.setRatePerHour( tariff.getRatePerHour() );

        return tariffDto;
    }

    @Override
    public Tariff toTariff(TariffDto tariffDto) {
        if ( tariffDto == null ) {
            return null;
        }

        Tariff tariff = new Tariff();

        tariff.setName( tariffDto.getName() );
        tariff.setDescription( tariffDto.getDescription() );
        tariff.setCarType( tariffDto.getCarType() );
        tariff.setRatePerHour( tariffDto.getRatePerHour() );

        return tariff;
    }

    @Override
    public List<TariffDto> toTariffDtos(List<Tariff> tariffs) {
        if ( tariffs == null ) {
            return null;
        }

        List<TariffDto> list = new ArrayList<TariffDto>( tariffs.size() );
        for ( Tariff tariff : tariffs ) {
            list.add( toTariffDto( tariff ) );
        }

        return list;
    }
}
