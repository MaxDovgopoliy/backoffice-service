package com.service.backoffice.services.implementation;

import com.service.backoffice.model.Order;
import com.service.backoffice.repositories.OrderRepo;
import com.service.backoffice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public List<Order> getOrderHistoryByUser(int userId, LocalDate dateStart, LocalDate dateEnd, String carType) {

        LocalDateTime startDateTime = dateStart == null ? LocalDateTime.of(1900, 1, 1, 0, 0) :
                LocalDateTime.of(dateStart, LocalTime.MIN);
        LocalDateTime endDateTime = dateEnd == null ? LocalDateTime.now() :
                LocalDateTime.of(dateEnd, LocalTime.MAX);

        List<Order> ordersByDate = orderRepo.findAllByUserIdAndStartDateBetween(userId, startDateTime, endDateTime);

        List<Order> filteredOrders = carType == null ? ordersByDate :
                ordersByDate.stream().filter(o -> carType.equals(o.getCarType())).collect(Collectors.toList());

        return filteredOrders;
    }
    @Override
    public List<Order> getAllOrderHistory(LocalDate dateStart, LocalDate dateEnd, String carType) {
        LocalDateTime startDateTime = dateStart == null ? LocalDateTime.of(1900, 1, 1, 0, 0) :
                LocalDateTime.of(dateStart, LocalTime.MIN);
        LocalDateTime endDateTime = dateEnd == null ? LocalDateTime.now() :
                LocalDateTime.of(dateEnd, LocalTime.MAX);

        List<Order> ordersByDate = orderRepo.findAllByStartDateBetween(startDateTime,endDateTime);
        List<Order> filteredOrders = carType == null ? ordersByDate :
                ordersByDate.stream().filter(o -> carType.equals(o.getCarType())).collect(Collectors.toList());

        return filteredOrders;
    }
}
