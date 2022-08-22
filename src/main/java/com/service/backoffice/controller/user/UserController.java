package com.service.backoffice.controller.user;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/user")
public class UserController {
    private final TariffServiceImpl tariffServiceImpl;
    private final OrderService orderService;
    private final AreaService areaService;

    public UserController(OrderService orderService,
                          TariffServiceImpl tariffServiceImpl, AreaService areaService) {
        this.orderService = orderService;
        this.tariffServiceImpl = tariffServiceImpl;
        this.areaService = areaService;
    }

    @GetMapping("/tariffs")
    public ResponseEntity<List<TariffDto>> getAllTariffs() {
        return ResponseEntity.status(HttpStatus.OK).body(tariffServiceImpl.getAllTariffs());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderDto>> getOrdersHistoryByUser(@PathVariable("id") int userId,
                                                                 @RequestParam(required = false)
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                 LocalDate dateStart,
                                                                 @RequestParam(required = false)
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                 LocalDate dateEnd,
                                                 @RequestParam(required = false) String carType) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderHistoryByUser(userId, dateStart,
                dateEnd, carType));
    }

    @GetMapping("/areas")
    public ResponseEntity<List<AreaDto>> getAllAreas() {
        return ResponseEntity.status(HttpStatus.OK).body(areaService.getAllAreas());
    }

}
