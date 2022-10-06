package com.service.backoffice.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TariffMapperTest {
//    private  final TariffMapper mapper= Mappers.getMapper(TariffMapper.class);
//    private List<Tariff> tariffs=List.of(
//            new Tariff(1L,"tariff1","description1","sedan",123),
//            new Tariff(2L,"tariff2","description2","moto",200)
//    );
//private List<TariffDto> tariffDtos =List.of(
//            new TariffDto("tariff1","description1","sedan",123),
//            new TariffDto("tariff2","description2","moto",200)
//    );

    @Test
    void toTariffDTO() {
//        TariffDto tariffDTO= mapper.toTariffDto(tariffs.get(0));
//
//        assertEquals(tariffDTO.getName(),tariffs.get(0).getName());
//        assertEquals(tariffDTO.getCarType(),tariffs.get(0).getCarType());
//        assertEquals(tariffDTO.getRatePerHour(),tariffs.get(0).getRatePerHour());
    }

    @Test
    void toTariff() {
//        Tariff tariff=mapper.toTariff(tariffDtos.get(1));
//
//        assertEquals(tariff.getName(), tariffDtos.get(1).getName());
//        assertEquals(tariff.getCarType(), tariffDtos.get(1).getCarType());
//        assertEquals(tariff.getRatePerHour(), tariffDtos.get(1).getRatePerHour());
    }

    @Test
    void toTariffDTOs() {
//        List<TariffDto> tariffDtos =mapper.toTariffDtos(tariffs);
//
//        assertEquals(tariffs.size(), tariffDtos.size());
//        assertEquals(tariffs.get(0).getCarType(), tariffDtos.get(0).getCarType());
//        assertEquals(tariffs.get(0).getName(), tariffDtos.get(0).getName());
//        assertEquals(tariffs.get(0).getDescription(), tariffDtos.get(0).getDescription());
//
//        assertEquals(tariffs.get(1).getCarType(), tariffDtos.get(1).getCarType());
//        assertEquals(tariffs.get(1).getName(), tariffDtos.get(1).getName());
//        assertEquals(tariffs.get(1).getRatePerHour(), tariffDtos.get(1).getRatePerHour());
    }
}