package com.service.backoffice.controller.user;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CountryMapper;
import com.service.backoffice.mapper.OrderMapper;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Address;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.LocationService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import java.math.BigDecimal;
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
    @MockBean
    private TariffServiceImpl tariffService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private AreaService areaService;
    @MockBean
    private LocationService locationService;

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
                LocalDateTime.now(), new BigDecimal(250), 1, "sedan", 1);
        OrderDto orderDto2 = new OrderDto(LocalDateTime.of(2020, 3, 1, 0, 0, 0),
                LocalDateTime.now(), new BigDecimal(240), 4, "moto", 3);

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
                .andExpect(jsonPath("$[0].price").value(orders.get(0).getPrice()))
                .andExpect(jsonPath("$[0].carId").value(orders.get(0).getCarId()));
    }

    @Test
    void getAllAreas() throws Exception {
          Country country = new Country("Ukraine");
          City city =new City("Lviv",500,country);
          List<Area> areas =
                List.of(new Area(240, new Address("Shevchenka",21), city),
                        new Area(240, new Address("Shevchenka",22), city),
                        new Area(240, new Address("Shevchenka",23), city));
        List<AreaDto> expectedAreaDtos= AreaMapper.MAPPER.toAreaDtos(areas);

        given(areaService.getAllAreas(null, null)).willReturn(areas);

        mockMvc.perform(get("/user/areas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedAreaDtos.size())))
                .andExpect(jsonPath("$[0].countryName").value(expectedAreaDtos.get(0).getCountryName()))
                .andExpect(jsonPath("$[1].cityName").value(expectedAreaDtos.get(1).getCityName()))
                .andExpect(jsonPath("$[1].address").value(expectedAreaDtos.get(1).getAddress()))
                .andExpect(jsonPath("$[2].square").value(expectedAreaDtos.get(1).getSquare()));
    }

    @Test
    void getAllCountries() throws Exception {
        List<Country> countries = List.of(new Country("Ukraine"),
                                          new Country("Sweden"));
        List<CountryDto> expectedCountryDtos= CountryMapper.MAPPER.toCountryDtos(countries);

        given(locationService.getAllCountries()).willReturn(countries);

        mockMvc.perform(get("/user/countries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedCountryDtos.size())))
                .andExpect(jsonPath("$[0].name").value(expectedCountryDtos.get(0).getName()))
                .andExpect(jsonPath("$[1].name").value(expectedCountryDtos.get(1).getName()));
    }

    @Test
    void getAllCities() throws Exception {
        Country country = new Country("Ukraine");
        List<City> cities =
                List.of(new City("Lviv", 500, country),
                        new City("Kyiv", 700, country),
                        new City("Lviv", 800, country));

        List<CityDto> expectedCityDtos= CityMapper.MAPPER.toCityDtos(cities);

        given(locationService.getAllCities()).willReturn(cities);

        mockMvc.perform(get("/user/cities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(expectedCityDtos.size())))
                .andExpect(jsonPath("$[0].name").value(expectedCityDtos.get(0).getName()))
                .andExpect(jsonPath("$[0].square").value(expectedCityDtos.get(0).getSquare()))
                .andExpect(jsonPath("$[0].countryName").value(expectedCityDtos.get(0).getCountryName()))
                .andExpect(jsonPath("$[1].countryName").value(expectedCityDtos.get(1).getCountryName()))
                .andExpect(jsonPath("$[2].square").value(expectedCityDtos.get(2).getSquare()));
    }
}