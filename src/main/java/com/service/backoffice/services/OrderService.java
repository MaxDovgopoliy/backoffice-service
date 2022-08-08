package com.service.backoffice.services;

import com.service.backoffice.dto.OrderDTO;
import com.service.backoffice.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrderHistoryByUser(int userId, LocalDate dateStart, LocalDate dateEnd, String carType);

    List<OrderDTO> getAllOrderHistory(LocalDate dateStart, LocalDate dateEnd, String carType);
}
