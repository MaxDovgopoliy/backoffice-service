package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.mapper.TariffMapper;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.services.TariffService;
import com.service.backoffice.util.security.Roles;
import com.service.backoffice.util.security.SecurityUtil;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/manager")
public class TariffController {
    private TariffService tariffService;
    private TariffMapper tariffMapper;
    private final SecurityUtil securityUtil;

    public TariffController(TariffService tariffService, TariffMapper tariffMapper,
                            SecurityUtil securityUtil) {
        this.tariffService = tariffService;
        this.tariffMapper = tariffMapper;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/tariffs")
    public ResponseEntity<TariffDto> addTariff(@RequestBody @Valid TariffDto tariffDto,
                                               @RequestHeader(required = false)
                                               String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(tariffMapper.toTariffDto(tariffService.saveTariff(tariffDto)));
    }

    @DeleteMapping("/tariffs/{id}")
    public ResponseEntity<Boolean> deleteTariff(@PathVariable("id") long tariffId,
                                                @RequestHeader(required = false)
                                                String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK).body(tariffService.deleteTariff(tariffId));
    }

    @GetMapping("/tariffs/{id}")
    public ResponseEntity<TariffDto> getTariff(@PathVariable long id,
                                               @RequestHeader(required = false)
                                               String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK)
                .body(tariffMapper.toTariffDto(tariffService.getTariffById(id)));
    }

    @PutMapping("/tariffs/{id}")
    public ResponseEntity<TariffDto> updateTariff(@PathVariable("id") long tariffId,
                                                  @RequestBody Tariff newTariff,
                                                  @RequestHeader(required = false)
                                                  String authorization) {
        securityUtil.tokenCheckForRole(authorization, Set.of(Roles.ADMIN));
        return ResponseEntity.status(HttpStatus.OK).body(
                tariffMapper.toTariffDto(tariffService.updateTariff(tariffId, newTariff)));
    }
}
