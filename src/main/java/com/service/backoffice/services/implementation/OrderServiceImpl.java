package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.mapper.OrderMapper;
import com.service.backoffice.model.Order;
import com.service.backoffice.repositories.OrderRepo;
import com.service.backoffice.services.OrderService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<OrderDto> getOrderHistoryByUser(int userId, LocalDate dateStart,
                                                LocalDate dateEnd, String carType) {

        LocalDateTime startDateTime = dateStart == null ? LocalDateTime.of(1900, 1, 1, 0, 0) :
                LocalDateTime.of(dateStart, LocalTime.MIN);
        LocalDateTime endDateTime = dateEnd == null ? LocalDateTime.now() :
                LocalDateTime.of(dateEnd, LocalTime.MAX);

        List<Order> ordersByDate = orderRepo.findAllByUserIdAndStartDateTimeBetween(
                userId, startDateTime, endDateTime);

        List<Order> filteredOrders = carType == null ? ordersByDate :
                ordersByDate.stream().filter(o -> carType.equals(o.getCarType()))
                        .collect(Collectors.toList());

        return OrderMapper.MAPPER.toOrderDtos(filteredOrders);
    }

    @Override
    public List<OrderDto> getAllOrderHistory(LocalDate dateStart,
                                             LocalDate dateEnd, String carType) {
        LocalDateTime startDateTime = dateStart == null ? LocalDateTime.of(1900, 1, 1, 0, 0, 0) :
                LocalDateTime.of(dateStart, LocalTime.MIN);
        LocalDateTime endDateTime = dateEnd == null ? LocalDateTime.now() :
                LocalDateTime.of(dateEnd, LocalTime.MAX);

        List<Order> ordersByDate = orderRepo
                .findAllByStartDateTimeBetween(startDateTime, endDateTime);
        List<Order> filteredOrders = carType == null ? ordersByDate :
                ordersByDate.stream().filter(o -> carType.equals(o.getCarType()))
                        .collect(Collectors.toList());

        return OrderMapper.MAPPER.toOrderDtos(filteredOrders);
    }
}
