package com.service.backoffice.controller;

import com.service.backoffice.dto.AreaDTO;
import com.service.backoffice.dto.OrderDTO;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Component
@RestController
@RequestMapping("/user")
public class UserController {
    private final TariffServiceImpl tariffServiceImpl;
    private final OrderService orderService;
    private final AreaService areaService;

    public UserController(OrderService orderService, TariffServiceImpl tariffServiceImpl, AreaService areaService) {
        this.orderService = orderService;
        this.tariffServiceImpl = tariffServiceImpl;
        this.areaService = areaService;
    }

    @GetMapping("/tariffs")
    public List<TariffDTO> getAllTariffs() {
        List<TariffDTO> tariffsDTOs = tariffServiceImpl.getAllTariffs();
        return tariffsDTOs;
    }

    @GetMapping("/orders/{id}")
    public List<OrderDTO> getOrdersHistoryByUser(@PathVariable("id") int userId,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                                              @RequestParam(required = false) String carType) {
        List<OrderDTO> orders = orderService.getOrderHistoryByUser(userId,dateStart,dateEnd,carType);
        return orders;
    }

    @GetMapping("/areas")
    public List<AreaDTO> getAllAreas() {
        List<AreaDTO> areas = areaService.getAllAreas();
        return areas;
    }

}
