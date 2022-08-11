package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.TariffService;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Component
@RestController
@RequestMapping("/manager")
public class TariffController {
    private TariffService tariffService;


    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @PostMapping("/tariff/add")
    public TariffDTO addTariff(@RequestParam @NotNull String name,
                               @RequestParam(required = false, defaultValue = "No description") String description,
                               @RequestParam @NotNull @Positive int ratePerHour,
                               @RequestParam @NotNull String carType) {
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
