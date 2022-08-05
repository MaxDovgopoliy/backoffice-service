package com.service.backoffice.controller;

import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.OrderServiceImpl;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserController(OrderService orderService, TariffServiceImpl tariffServiceImpl) {
        this.orderService = orderService;
        this.tariffServiceImpl = tariffServiceImpl;
    }

    @GetMapping("/tariffs")
    public Iterable<Tariff> getAllTariffs() {
        Iterable<Tariff> tariffs = tariffServiceImpl.getAllTariffs();
        return tariffs;
    }

    @GetMapping("/orders/{id}")
    public List<Order> getOrdersHistoryByUser(@PathVariable("id") int userId,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                                              @RequestParam(required = false) String carType) {
        List<Order> orders = orderService.getOrderHistoryByUser(userId,dateStart,dateEnd,carType);
        return orders;
    }

}
