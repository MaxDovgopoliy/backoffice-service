package com.service.backoffice.kafka;

import com.service.backoffice.dto.OrderDtoFromTripService;
import com.service.backoffice.mapper.MapperForOrder;
import com.service.backoffice.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {
    private final OrderService orderService;
    private final MapperForOrder mapperForOrder;

    public KafkaListeners(OrderService orderService, MapperForOrder mapperForOrder) {
        this.orderService = orderService;
        this.mapperForOrder = mapperForOrder;
    }

    @KafkaListener(
            topics = "finish-order",
            groupId = "None",
            containerFactory = "orderKafkaListenerContainerFactory"
    )
    void orderListener(OrderDtoFromTripService orderDtoFromTripService) {
        log.info("orderDto={}",orderDtoFromTripService.toString());
        orderService.saveOrder(
                mapperForOrder.toOrderDtoFromTripService(orderDtoFromTripService));
    }
}
