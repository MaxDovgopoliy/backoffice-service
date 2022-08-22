package com.service.backoffice.controller.user;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.OrderMapper;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;
    @MockBean
    private TariffServiceImpl tariffService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private AreaService areaService;

    @Test
    void getAllTariffs() throws Exception {

        TariffDto tariff1 = new TariffDto("tariff1", "description", "sedan", 120);
        TariffDto tariff2 = new TariffDto("tariff2", "description", "moto", 130);
        TariffDto tariff3 = new TariffDto("tariff3", "description", "moto", 135);
        List<TariffDto> tariffDtos = new ArrayList<>(List.of(tariff1, tariff2, tariff3));
        List<Tariff> tariffs = TariffMapper.MAPPER.toTariffs(tariffDtos);

        given(tariffService.getAllTariffs()).willReturn(tariffs);


        mockMvc.perform(get("/user/tariffs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(tariffDtos.size())))
                .andExpect(jsonPath("$[0].name").value(tariffDtos.get(0).getName()))
                .andExpect(jsonPath("$[1].carType").value(tariffDtos.get(1).getCarType()))
                .andExpect(jsonPath("$[2].ratePerHour").value(tariffDtos.get(2).getRatePerHour()));
    }

    @Test
    void getOrdersHistoryByUser() throws Exception {
        OrderDto orderDto1 = new OrderDto(LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.now(), 250, 1, "sedan", 1);
        OrderDto orderDto2 = new OrderDto(LocalDateTime.of(2020, 3, 1, 0, 0, 0),
                LocalDateTime.now(), 240, 4, "moto", 3);

        List<OrderDto> orderDtos = List.of(orderDto1, orderDto2);
        List<Order> orders = OrderMapper.MAPPER.toOrders(orderDtos);

        given(orderService.getOrderHistoryByUser(1, null, null, null)).willReturn(
                (List.of(orders.get(0))));


        mockMvc.perform(get("/user/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].startDateTime").value(orders.get(0).getStartDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .andExpect(jsonPath("$[0].endDateTime").value(orders.get(0).getEndDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .andExpect(jsonPath("$[0].carType").value(orders.get(0).getCarType()))
                .andExpect(jsonPath("$[0].prise").value(orders.get(0).getPrise()))
                .andExpect(jsonPath("$[0].carId").value(orders.get(0).getCarId()));
    }

    @Test
    void getAllAreas() throws Exception {
        List<Coordinates> listOfCoordinates1 = List.of(new Coordinates(1L, 123.32, 234.32),
                new Coordinates(2L, 123.32, 234.32),
                new Coordinates(3L, 123.32, 234.32));
        List<Coordinates> listOfCoordinates2 = List.of(new Coordinates(4L, 766.56, 876.45),
                new Coordinates(5L, 123.32, 234.32),
                new Coordinates(6L, 123.32, 234.32));
        List<Area> areas = List.of(new Area(1L, "Ukraine", "Lviv", listOfCoordinates1),
                new Area(2L, "Ukraine", "Kyiv", listOfCoordinates2));

        List<AreaDto> expectedAreaDtos= AreaMapper.MAPPER.toAreaDtos(areas);

        given(areaService.getAllAreas()).willReturn(areas);

        mockMvc.perform(get("/user/areas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedAreaDtos.size())))
                .andExpect(jsonPath("$[0].country").value(expectedAreaDtos.get(0).getCountry()))
                .andExpect(jsonPath("$[1].city").value(expectedAreaDtos.get(1).getCity()))
                .andExpect(jsonPath("$[1].coordinates.size()").value(expectedAreaDtos.get(1).getCoordinates().size()));
    }
}