package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.TariffService;
import com.sun.istack.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/tariffs")
    public ResponseEntity<TariffDto> addTariff(@RequestParam @NotNull String name,
                                               @RequestParam(required = false,
                                               defaultValue = "No description")
                                                String description,
                                               @RequestParam @NotNull @Positive int ratePerHour,
                                               @RequestParam @NotNull String carType) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tariffService.saveTariff(name, description, carType, ratePerHour));
    }

    @DeleteMapping("/tariffs/{id}")
    public ResponseEntity<Boolean> deleteTariff(@PathVariable("id") long tariffId) {
        return ResponseEntity.status(HttpStatus.OK).body(tariffService.deleteTariff(tariffId));
    }

    @GetMapping("/tariffs/{id}")
    public ResponseEntity<TariffDto> getTariff(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tariffService.getTariffById(id));
    }

    @PutMapping("/tariffs/{id}")
    public ResponseEntity<TariffDto> updateTariff(@PathVariable("id") long tariffId,
                                                  @RequestBody Tariff newTariff) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tariffService.updateTariff(tariffId, newTariff));
    }
}
