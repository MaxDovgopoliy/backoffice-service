package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.TariffService;
import com.sun.istack.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/manager")
public class TariffController {
    private TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @PostMapping("/tariff/add")
    public TariffDto addTariff(@RequestParam @NotNull String name,
                               @RequestParam(required = false, defaultValue = "No description")
                               String description,
                               @RequestParam @NotNull @Positive int ratePerHour,
                               @RequestParam @NotNull String carType) {
        return tariffService.saveTariff(name, description, carType, ratePerHour);
    }

    @DeleteMapping("/delete/tariff/{id}")
    public boolean deleteTariff(@PathVariable("id") long tariffId) {
        return tariffService.deleteTariff(tariffId);
    }

    @GetMapping("/tariff/{id}")
    public TariffDto getTariff(@PathVariable long id) {
        return tariffService.getTariffById(id);
    }

    @PutMapping("/update/tariff/{id}")
    public TariffDto updateTariff(@PathVariable("id") long tariffId,
                                  @RequestBody Tariff newTariff) {
        return tariffService.updateTariff(tariffId, newTariff);
    }
}
