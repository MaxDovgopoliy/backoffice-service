package com.service.backoffice.controller;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class AdminController {
    private TariffRepo tariffRepo;

    public AdminController(TariffRepo tariffRepo) {
        this.tariffRepo = tariffRepo;
    }


    @PostMapping ("/tariff/add")
    public Tariff addTariff(@RequestParam String name,@RequestParam(required = false,defaultValue = "") String description,
                            @RequestParam int ratePerHour,@RequestParam String carType){
        tariffRepo.save(new Tariff(name,description,carType,ratePerHour));
        return tariffRepo.findByName(name);
    }
}
