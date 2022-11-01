package com.service.backoffice.services.implementation;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.mapper.MapperForOrder;
import com.service.backoffice.model.Order;
import com.service.backoffice.repositories.OrderRepo;
import com.service.backoffice.repositories.TariffRepo;
import com.service.backoffice.services.OrderService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MapperForOrder mapperForOrder;
    @Autowired
    private TariffRepo tariffRepo;

    @Override
    public List<Order> getOrderHistoryByUser(int userId, LocalDate dateStart,
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

        return filteredOrders;
    }

    @Override
    public List<Order> getAllOrderHistory(LocalDate dateStart,
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

        return filteredOrders;
    }

    @Override
    @Transactional
    public Order saveOrder(OrderDto orderDto) {
        Order order = mapperForOrder.toOrder(orderDto);
        //Tariff tariff = tariffRepo.findById(orderDto.getTariffId()).get();

        return orderRepo.save(order);
    }

    @Override
    public List<Order> getOrderHistoryByCar(int carId, LocalDate dateStart, LocalDate dateEnd) {
        LocalDateTime startDateTime = dateStart == null ? LocalDateTime.of(1900, 1, 1, 0, 0) :
                LocalDateTime.of(dateStart, LocalTime.MIN);
        LocalDateTime endDateTime = dateEnd == null ? LocalDateTime.now() :
                LocalDateTime.of(dateEnd, LocalTime.MAX);

        return orderRepo.findAllByCarIdAndStartDateTimeBetween(
                carId, startDateTime, endDateTime);
    }
}
