package com.service.backoffice.services;

import com.service.backoffice.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getOrderHistoryByUser(int userId, LocalDate dateStart, LocalDate dateEnd, String carType);

}
