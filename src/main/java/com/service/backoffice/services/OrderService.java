package com.service.backoffice.services;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.model.Order;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getOrderHistoryByUser(int userId, LocalDate dateStart,
                                      LocalDate dateEnd, String carType);

    List<Order> getAllOrderHistory(LocalDate dateStart, LocalDate dateEnd, String carType);

    Order saveOrder(OrderDto orderDto);

    List<Order> getOrderHistoryByCar(int carId, LocalDate dateStart, LocalDate dateEnd);
}
