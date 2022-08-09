package com.service.backoffice.mapper;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Tariff;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffMapperTest {
    private  final TariffMapper mapper= Mappers.getMapper(TariffMapper.class);
    private List<Tariff> tariffs=List.of(
            new Tariff(1L,"tariff1","description1","sedan",123),
            new Tariff(2L,"tariff2","description2","moto",200)
    );
private List<TariffDTO> tariffDTOs=List.of(
            new TariffDTO("tariff1","description1","sedan",123),
            new TariffDTO("tariff2","description2","moto",200)
    );

    @Test
    void toTariffDTO() {
        TariffDTO tariffDTO= mapper.toTariffDTO(tariffs.get(0));

        assertEquals(tariffDTO.getName(),tariffs.get(0).getName());
        assertEquals(tariffDTO.getCarType(),tariffs.get(0).getCarType());
        assertEquals(tariffDTO.getRatePerHour(),tariffs.get(0).getRatePerHour());
    }

    @Test
    void toTariff() {
        Tariff tariff=mapper.toTariff(tariffDTOs.get(1));

        assertEquals(tariff.getName(),tariffDTOs.get(1).getName());
        assertEquals(tariff.getCarType(),tariffDTOs.get(1).getCarType());
        assertEquals(tariff.getRatePerHour(),tariffDTOs.get(1).getRatePerHour());
    }

    @Test
    void toTariffDTOs() {
        List<TariffDTO> tariffDTOS =mapper.toTariffDTOs(tariffs);

        assertEquals(tariffs.size(),tariffDTOS.size());
        assertEquals(tariffs.get(0).getCarType(),tariffDTOS.get(0).getCarType());
        assertEquals(tariffs.get(0).getName(),tariffDTOS.get(0).getName());
        assertEquals(tariffs.get(0).getDescription(),tariffDTOS.get(0).getDescription());

        assertEquals(tariffs.get(1).getCarType(),tariffDTOS.get(1).getCarType());
        assertEquals(tariffs.get(1).getName(),tariffDTOS.get(1).getName());
        assertEquals(tariffs.get(1).getRatePerHour(),tariffDTOS.get(1).getRatePerHour());
    }
}