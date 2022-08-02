package com.service.backoffice.controller;

import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.OrderServiceImpl;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public List<Order> getOrdersHistoryByUser(@PathVariable("id") long userId,
                                              @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDateTime).of(2000, Month.JANUARY, 1)}")
                                              LocalDate dateStart,
                                              @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate dateEnd,
                                              @RequestParam(required = false,defaultValue="") String carType) {
        List<Order> orders = orderService.getOrderHistory(userId,dateStart,dateEnd,carType);
        return orders;
    }

}
