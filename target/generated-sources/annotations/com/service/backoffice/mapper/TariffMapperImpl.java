package com.service.backoffice.mapper;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Tariff;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T12:36:32+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Amazon.com Inc.)"
)
public class TariffMapperImpl implements TariffMapper {

    @Override
    public TariffDTO toTariffDTO(Tariff tariff) {
        if ( tariff == null ) {
            return null;
        }

        TariffDTO tariffDTO = new TariffDTO();

        tariffDTO.setName( tariff.getName() );
        tariffDTO.setDescription( tariff.getDescription() );
        tariffDTO.setCarType( tariff.getCarType() );
        tariffDTO.setRatePerHour( tariff.getRatePerHour() );

        return tariffDTO;
    }

    @Override
    public Tariff toTariff(TariffDTO tariffDTO) {
        if ( tariffDTO == null ) {
            return null;
        }

        Tariff tariff = new Tariff();

        tariff.setName( tariffDTO.getName() );
        tariff.setDescription( tariffDTO.getDescription() );
        tariff.setCarType( tariffDTO.getCarType() );
        tariff.setRatePerHour( tariffDTO.getRatePerHour() );

        return tariff;
    }

    @Override
    public List<TariffDTO> toTariffDTOs(List<Tariff> tariffs) {
        if ( tariffs == null ) {
            return null;
        }

        List<TariffDTO> list = new ArrayList<TariffDTO>( tariffs.size() );
        for ( Tariff tariff : tariffs ) {
            list.add( toTariffDTO( tariff ) );
        }

        return list;
    }
}
