package com.service.backoffice.mapper;

import com.service.backoffice.repositories.CountryRepo;
import com.service.backoffice.repositories.TariffRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderMapperTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    CountryRepo countryRepo;
    @MockBean
    TariffRepo tariffRepo;
    @MockBean
    MapperForOrder mapperForOrder;

    @Test
    void toOrderDtoFromTripService() {
//        OrderDtoFromTripService orderDtoFromTripService = new OrderDtoFromTripService(
//                LocalDateTime.MIN,LocalDateTime.now(),new BigDecimal(300),1,20,1);
//
//        Tariff tariff =new Tariff(1l,"tariff","good one","medium",20);
//
//        when(tariffRepo.findByRatePerHour(orderDtoFromTripService.getRatePerHour())).thenReturn(tariff);
//
//        OrderDto orderDto =
//                mapperForOrder.toOrderDtoFromTripService(orderDtoFromTripService);
//
//        assertEquals(tariff.getCarType(),orderDto.getCarType());
//        assertEquals(orderDtoFromTripService.getPrice(),orderDto.getPrice());
//        assertEquals(orderDtoFromTripService.getEndDateTime(),orderDto.getEndDateTime());

    }
}