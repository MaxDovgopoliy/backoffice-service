package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.TariffService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
@Component
@RestController
@RequestMapping("/manager")
public class TariffController {
    private TariffService tariffService;
    private TariffRepo tariffRepo;

    public TariffController(TariffService tariffService, TariffRepo tariffRepo, OrderService orderService, AreaService areaService) {
        this.tariffService = tariffService;
        this.tariffRepo = tariffRepo;
    }

    @PostMapping("/tariff/add")
    public TariffDTO addTariff(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String description,
                            @RequestParam int ratePerHour, @RequestParam String carType) {
        return tariffService.saveTariff(name, description, carType, ratePerHour);
    }

    @DeleteMapping("/delete/tariff/{id}")
    public boolean deleteTariff(@PathVariable("id") long tariffId) {
        return tariffService.deleteTariff(tariffId);
    }

    @GetMapping("/tariff/{id}")
    public TariffDTO getTariff(@PathVariable long id) {
        return tariffService.getTariffById(id);
    }

    @PutMapping("/update/tariff/{id}")
    public TariffDTO updateTariff(@PathVariable("id") long tariffId, @RequestBody Tariff newTariff) {
        return tariffService.updateTariff(tariffId, newTariff);

    }
}
