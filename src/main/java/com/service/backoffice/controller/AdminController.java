package com.service.backoffice.controller;

import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.TariffService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class AdminController {
    private TariffService tariffService;
    private TariffRepo tariffRepo;

    public AdminController(TariffService tariffService, TariffRepo tariffRepo) {
        this.tariffService = tariffService;
        this.tariffRepo = tariffRepo;
    }


    @PostMapping ("/tariff/add")
    public Tariff addTariff(@RequestParam String name,@RequestParam(required = false,defaultValue = "") String description,
                            @RequestParam int ratePerHour,@RequestParam String carType){
        tariffService.saveTariff(name,description,carType,ratePerHour);
        return tariffRepo.findByName(name);
    }

    @DeleteMapping("/delete/tariff/{id}")
    public boolean deleteTariff(@PathVariable("id") long tariffId){
        return tariffService.deleteTariff(tariffId);
    }
}
