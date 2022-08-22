package com.service.backoffice.controller.manager;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.mapper.OrderMapper;
import com.service.backoffice.services.OrderService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrdersHistory(@RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                              LocalDate dateStart,
                                              @RequestParam(required = false)
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                              LocalDate dateEnd,
                                              @RequestParam(required = false) String carType) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(OrderMapper.MAPPER.toOrderDtos(
                        orderService.getAllOrderHistory(dateStart, dateEnd, carType)));
    }
}
