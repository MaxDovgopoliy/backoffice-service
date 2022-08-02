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
    public List<Order> getOrderHistory(long userId, LocalDate dateStart, LocalDate dateEnd, String carType) {
        List<Order> ordersByDate = orderRepo.findAllByUserIdAndStartDateBetween(userId, dateStart, dateEnd);
        List<Order> filteredOrders;
        filteredOrders = carType.equals("") ? ordersByDate : ordersByDate.stream().filter(o -> carType.equals(o.getCarType())).collect(Collectors.toList());
        return filteredOrders;
    }
}
