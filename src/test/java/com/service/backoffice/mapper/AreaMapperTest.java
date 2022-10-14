package com.service.backoffice.mapper;

import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.CountryRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AreaMapperTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CountryRepo countryRepo;
    @MockBean
    CityRepo cityRepo;

AreaMapper mapper= Mappers.getMapper(AreaMapper.class);
//    private static Country country = new Country("Ukraine");
//    private static City city =new City("Lviv",500,country);
//    private static List<Area> areas =
//            List.of(new Area(240, new Address("Shevchenka",21), city),
//                    new Area(240, new Address("Shevchenka",22), city),
//                    new Area(240, new Address("Shevchenka",23), city));

    @Test
    void toAreaDTO() {
//        Area area = areas.get(0);
//        AreaDto areaDTO= mapper.toAreaDto(area);
//
//        assertEquals(area.getSquare(),areaDTO.getSquare());
//        assertEquals(area.getCity().getName(),areaDTO.getCityName());
//        assertEquals(area.getCity().getCountry().getName(),areaDTO.getCountryName());
//        assertEquals(area.getAddress(),AddressMapper.MAPPER.toAddress(areaDTO.getAddress()));
    }

    @Test
    void toArea() {
//        Area area = areas.get(0);
//        when(countryRepo.findByNameIgnoreCase(area.getCity().getCountry().getName())).thenReturn(country);
//        AreaDto areaDTO= mapper.toAreaDto(area);
//
//
//        assertEquals(area.getAddress(),AddressMapper.MAPPER.toAddress(areaDTO.getAddress()));
//        assertEquals(area.getSquare(),areaDTO.getSquare());
//        assertEquals(area.getCity().getName(),areaDTO.getCityName());
    }

    @Test
    void toAreaDTOs() {
//        List<AreaDto> areaDtos = mapper.toAreaDtos(areas);
//
//        assertEquals(areaDtos.get(0).getCountryName(),areas.get(0).getCity().getCountry().getName());
//        assertEquals(areaDtos.get(0).getCityName(),areas.get(0).getCity().getName());
//        assertEquals(areaDtos.get(1).getSquare(),areas.get(1).getSquare());
//        assertEquals(areaDtos.get(2).getSquare(),areas.get(2).getSquare());
//        assertEquals(AddressMapper.MAPPER.toAddress(areaDtos.get(2).getAddress()),areas.get(2).getAddress());
    }

}