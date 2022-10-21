package com.service.backoffice.controller.user;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CityDto;
import com.service.backoffice.dto.CountryDto;
import com.service.backoffice.dto.DtoForTripService;
import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.mapper.AreaMapper;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CountryMapper;
import com.service.backoffice.mapper.MapperForOrder;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.LocationService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import com.service.backoffice.util.AreaUtil;
import com.service.backoffice.util.security.Roles;
import com.service.backoffice.util.security.SecurityUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
    private final TariffMapper tariffMapper;

    private final MapperForOrder mapperForOrder;
    private final SecurityUtil securityUtil;

    public UserController(OrderService orderService,
                          TariffServiceImpl tariffServiceImpl, AreaService areaService,
                          LocationService locationService, TariffMapper tariffMapper,
                          MapperForOrder mapperForOrder, SecurityUtil securityUtil) {
        this.orderService = orderService;
        this.tariffServiceImpl = tariffServiceImpl;
        this.areaService = areaService;
        this.locationService = locationService;
        this.tariffMapper = tariffMapper;
        this.mapperForOrder = mapperForOrder;
        this.securityUtil = securityUtil;
    }

    @GetMapping("/tariffs")
    public ResponseEntity<List<TariffDto>> getAllTariffs(@RequestParam String countryName,
                                                         @RequestParam String cityName,
                                                         @RequestHeader(required = false)
                                                         String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN));

        return ResponseEntity.status(HttpStatus.OK).body(
                tariffMapper.toTariffDtos(tariffServiceImpl.getAllTariffs(countryName, cityName)));

    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersHistoryByUser(@PathVariable("userId") int userId,
                                                                 @RequestParam(required = false)
                                                                 @DateTimeFormat(iso =
                                                                         DateTimeFormat.ISO.DATE)
                                                                 LocalDate dateStart,
                                                                 @RequestParam(required = false)
                                                                 @DateTimeFormat(iso =
                                                                         DateTimeFormat.ISO.DATE)
                                                                 LocalDate dateEnd,
                                                                 @RequestParam(required = false)
                                                                 String carType,
                                                                 @RequestHeader(required = false)
                                                                 String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapperForOrder.toOrderDtos(orderService.getOrderHistoryByUser(
                        userId, dateStart, dateEnd, carType)));
    }

    @GetMapping("/cars/{carId}/orders")
    public ResponseEntity<List<OrderDto>> getOrdersHistoryByCar(@PathVariable("carId") int carId,
                                                                @RequestParam(required = false)
                                                                @DateTimeFormat(iso =
                                                                        DateTimeFormat.ISO.DATE)
                                                                LocalDate dateStart,
                                                                @RequestParam(required = false)
                                                                @DateTimeFormat(iso =
                                                                        DateTimeFormat.ISO.DATE)
                                                                LocalDate dateEnd,
                                                                @RequestHeader(required = false)
                                                                String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.CAR_OWNER, Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapperForOrder.toOrderDtos(orderService.getOrderHistoryByCar(
                        carId, dateStart, dateEnd)));
    }

    @GetMapping("/areas")
    public ResponseEntity<List<AreaDto>> getAllAreas(@RequestParam(required = false)
                                                     String countryName,
                                                     @RequestParam(required = false)
                                                     String cityName,
                                                     @RequestParam(required = false,
                                                             defaultValue = "0")
                                                     double latitude,
                                                     @RequestParam(required = false,
                                                             defaultValue = "0")
                                                     double longitude,
                                                     @RequestHeader(required = false)
                                                     String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN));

        return ResponseEntity.status(HttpStatus.OK).body(
                AreaMapper.MAPPER.toAreaDtos(areaService.getAllAreas(countryName, cityName,
                        latitude, longitude)));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> getAllCountries(@RequestHeader(required = false)
                                                            String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK).body(
                CountryMapper.MAPPER.toCountryDtos(locationService.getAllCountries()));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getAllCities(@RequestHeader(required = false)
                                                      String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK).body(
                CityMapper.MAPPER.toCityDtos(locationService.getAllCities()));
    }

    @GetMapping("/tariffs/{carType}")
    public ResponseEntity<DtoForTripService> getTariffByCarType(@PathVariable
                                                                      @NotBlank
                                                                      @Pattern(
                                                                              regexp = "[a-z A-Z]+")
                                                                      String carType,
                                                                @RequestParam double latitude,
                                                                @RequestParam
                                                                      double longitude,
                                                                @RequestHeader(
                                                                              required = false)
                                                                      String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN,
                Roles.CAR_OWNER));
        return ResponseEntity.status(HttpStatus.OK).body(
                AreaUtil.informationForTrip(carType, latitude, longitude));
    }

    @GetMapping("/validate-coordinates-for-parking")
    public ResponseEntity<Boolean> validateParkingCoordinates(@RequestParam double latitude,
                                                      @RequestParam double longitude,
                                                      @RequestHeader(required = false)
                                                                      String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.USER, Roles.ADMIN,
                Roles.CAR_OWNER));
        return ResponseEntity.status(HttpStatus.OK).body(AreaUtil.isPossibleToLeaveCarByCoordinates(
                latitude, longitude));
    }
}
