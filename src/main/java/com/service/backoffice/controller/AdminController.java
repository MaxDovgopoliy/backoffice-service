package com.service.backoffice.controller;

import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.TariffService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Component
@RestController
@RequestMapping("/manager")
public class AdminController {
    private TariffService tariffService;
    private TariffRepo tariffRepo;
    private OrderService orderService;

    public AdminController(TariffService tariffService, TariffRepo tariffRepo, OrderService orderService) {
        this.tariffService = tariffService;
        this.tariffRepo = tariffRepo;
        this.orderService = orderService;
    }


    @PostMapping("/tariff/add")
    public Tariff addTariff(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String description,
                            @RequestParam int ratePerHour, @RequestParam String carType) {
        tariffService.saveTariff(name, description, carType, ratePerHour);
        return tariffRepo.findByName(name);
    }

    @DeleteMapping("/delete/tariff/{id}")
    public boolean deleteTariff(@PathVariable("id") long tariffId) {
        return tariffService.deleteTariff(tariffId);
    }

    @GetMapping("/tariff/{id}")
    public Tariff getTariff(@PathVariable long id) {
        return tariffService.getTariffById(id);
    }

    @PutMapping("/update/tariff/{id}")
    public Tariff updateTariff(@PathVariable("id") long tariffId, @RequestBody Tariff newTariff) {
        return tariffService.updateTariff(tariffId, newTariff);

    }
    @GetMapping("/orders")
    public List<Order> getAllOrdersHistory(@RequestParam(required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                           @RequestParam(required = false)
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                                           @RequestParam(required = false) String carType) {
        List<Order> orders = orderService.getAllOrderHistory(dateStart, dateEnd, carType);
        return orders;
    }
}
