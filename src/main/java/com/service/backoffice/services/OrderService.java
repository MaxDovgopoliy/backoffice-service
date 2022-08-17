package com.service.backoffice.services;

import com.service.backoffice.dto.OrderDto;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<OrderDto> getOrderHistoryByUser(int userId, LocalDate dateStart,
                                         LocalDate dateEnd, String carType);

    List<OrderDto> getAllOrderHistory(LocalDate dateStart, LocalDate dateEnd, String carType);
}
