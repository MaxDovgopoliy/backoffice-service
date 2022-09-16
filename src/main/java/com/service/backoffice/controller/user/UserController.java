package com.service.backoffice.controller.user;

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
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.LocationService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/user")
public class UserController {
    private final TariffServiceImpl tariffServiceImpl;
    private final OrderService orderService;
    private final AreaService areaService;
    private final LocationService locationService;

    public UserController(OrderService orderService,
                          TariffServiceImpl tariffServiceImpl, AreaService areaService,
                          LocationService locationService) {
        this.orderService = orderService;
        this.tariffServiceImpl = tariffServiceImpl;
        this.areaService = areaService;
        this.locationService = locationService;
    }

    @GetMapping("/tariffs")
    public ResponseEntity<List<TariffDto>> getAllTariffs() {
        return ResponseEntity.status(HttpStatus.OK).body(
                TariffMapper.MAPPER.toTariffDtos(tariffServiceImpl.getAllTariffs()));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersHistoryByUser(@PathVariable("id") int userId,
                                                                 @RequestParam(required = false)
                                                                 @DateTimeFormat(iso =
                                                                         DateTimeFormat.ISO.DATE)
                                                                 LocalDate dateStart,
                                                                 @RequestParam(required = false)
                                                                 @DateTimeFormat(iso =
                                                                         DateTimeFormat.ISO.DATE)
                                                                 LocalDate dateEnd,
                                                                 @RequestParam(required = false)
                                                                 String carType) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(OrderMapper.MAPPER.toOrderDtos(orderService.getOrderHistoryByUser(
                        userId, dateStart, dateEnd, carType)));
    }

    @GetMapping("/areas")
    public ResponseEntity<List<AreaDto>> getAllAreas(@RequestBody(required = false)
                                                     String countryName,
                                                     @RequestBody(required = false)
                                                     String cityName) {
        return ResponseEntity.status(HttpStatus.OK).body(
                AreaMapper.MAPPER.toAreaDtos(areaService.getAllAreas(countryName, cityName)));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.status(HttpStatus.OK).body(
                CountryMapper.MAPPER.toCountryDtos(locationService.getAllCountries()));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        return ResponseEntity.status(HttpStatus.OK).body(
                CityMapper.MAPPER.toCityDtos(locationService.getAllCities()));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> addOrder(@RequestBody @Valid OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                OrderMapper.MAPPER.toOrderDto(orderService.saveOrder(orderDto)));
    }

    @GetMapping("/tariffs/{carType}")
    public ResponseEntity<TariffDto> getTariffByCarType(@PathVariable
                                                            @NotBlank
                                                            @Pattern(regexp = "[a-z A-Z]+")
                                                            String carType) {
        return ResponseEntity.status(HttpStatus.OK).body(
                TariffMapper.MAPPER.toTariffDto(tariffServiceImpl.getTariffByCarType(carType)));
    }
}
