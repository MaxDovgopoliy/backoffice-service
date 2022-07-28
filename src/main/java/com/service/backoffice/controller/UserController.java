package com.service.backoffice.controller;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    TariffService tariffService;

    @GetMapping("/tariff")
    public Iterable<Tariff> getTariffs(){
        Iterable<Tariff> tariffs = tariffService.getAllTariffs();
        return tariffs;
    }
}
