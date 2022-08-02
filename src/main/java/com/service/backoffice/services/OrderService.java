package com.service.backoffice.services;

import com.service.backoffice.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getOrderHistory(long userId, LocalDate dateStart, LocalDate dateEnd, int carId);
}
