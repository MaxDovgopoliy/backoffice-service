package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.OrderDTO;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.TariffService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class OrderController {
    private OrderService orderService;


    public OrderController( OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrdersHistory(@RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                                              @RequestParam(required = false) String carType) {
        List<OrderDTO> orders = orderService.getAllOrderHistory(dateStart, dateEnd, carType);
        return orders;
    }
}
