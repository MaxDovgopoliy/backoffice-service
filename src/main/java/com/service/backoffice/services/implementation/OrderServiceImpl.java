package com.service.backoffice.services.implementation;

import com.service.backoffice.model.Order;
import com.service.backoffice.repositories.OrderRepo;
import com.service.backoffice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public List<Order> getOrderHistory(long userId, LocalDate dateStart, LocalDate dateEnd, int carId) {
        List<Order> ordersByDate = orderRepo.findAllByUserIdAndStartDateBetween(userId, dateStart, dateEnd);
        List<Order> filteredOrders;
        filteredOrders = carId == 0 ? ordersByDate : ordersByDate.stream().filter(o -> carId == o.getCarId()).collect(Collectors.toList());
        return filteredOrders;
    }
}
