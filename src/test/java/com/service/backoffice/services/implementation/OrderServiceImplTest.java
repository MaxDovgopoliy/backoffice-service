package com.service.backoffice.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.mapper.MapperForOrder;
import com.service.backoffice.model.Order;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.OrderRepo;
import com.service.backoffice.repositories.TariffRepo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepo orderRepo;
    @Mock
    TariffRepo tariffRepo;
    @Mock
    MapperForOrder mapperForOrder;
    Tariff tariff = new Tariff(1L, "tariff1", "description", "medium", 100, "UAH");
    private List<OrderDto> orderDtos = List.of(
            new OrderDto(LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                    LocalDateTime.now(), new BigDecimal(250), 1, "sedan", 2, 1),

            new OrderDto(LocalDateTime.of(2020, 3, 1, 0, 0, 0),
                    LocalDateTime.now(), new BigDecimal(240), 4, "moto", 3, 1));

    private List<Order> orders = List.of(new Order(1L, LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                    orderDtos.get(0).getEndDateTime(), new BigDecimal(250), "UAH", 1, "sedan", 2, tariff),
            new Order(1L, LocalDateTime.of(2020, 3, 1, 0, 0, 0),
                    orderDtos.get(1).getEndDateTime(), new BigDecimal(240), "UAH", 1, "sedan", 2,
                    tariff));

    private final LocalDateTime startDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0);

    private final LocalDate startDate = LocalDate.of(1900, 1, 1);
    private final LocalDate endDate = LocalDate.of(2021, 1, 1);
    private final LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

    @Test
    void getOrderHistoryByUser() {
        Order expectedOrder = orders.get(0);
        when(orderRepo.findAllByUserIdAndStartDateTimeBetween(2, startDateTime, endDateTime)).
                thenReturn(List.of(orders.get(0)));

        var resultOrders = orderService.getOrderHistoryByUser(2, startDate, endDate, "sedan");

        verify(orderRepo).findAllByUserIdAndStartDateTimeBetween(2, startDateTime, endDateTime);
        assertNotNull(resultOrders);
        assertEquals(expectedOrder, resultOrders.get(0));
    }

    @Test
    void getOrderHistoryByUserWithoutAdditionParams() {
        Order expectedOrder = orders.get(0);
        when(orderRepo.findAllByUserIdAndStartDateTimeBetween(anyInt(), any(LocalDateTime.class),
                any(LocalDateTime.class))).
                thenReturn(List.of(orders.get(0)));

        var resultOrders = orderService.getOrderHistoryByUser(2, null, null, null);

        verify(orderRepo).findAllByUserIdAndStartDateTimeBetween(anyInt(), any(LocalDateTime.class),
                any(LocalDateTime.class));
        assertNotNull(resultOrders);
        assertEquals(expectedOrder, resultOrders.get(0));

    }

    @Test
    void getAllOrderHistory() {
        List<Order> expectedOrders = orders;
        when(orderRepo.findAllByStartDateTimeBetween(startDateTime, endDateTime)).thenReturn(
                orders);


        var resultOrders = orderService.getAllOrderHistory(startDate, endDate, "sedan");

        verify(orderRepo).findAllByStartDateTimeBetween(startDateTime, endDateTime);
        assertNotNull(resultOrders);
        assertIterableEquals(expectedOrders, resultOrders);
    }

    @Test
    void getAllOrderHistoryWithoutAdditionParams() {
        List<Order> expectedOrders = orders;
        when(orderRepo.findAllByStartDateTimeBetween(any(LocalDateTime.class),
                any(LocalDateTime.class))).
                thenReturn(orders);

        var resultOrders = orderService.getAllOrderHistory(null, null, null);

        verify(orderRepo).findAllByStartDateTimeBetween(any(LocalDateTime.class),
                any(LocalDateTime.class));
        assertNotNull(resultOrders);
        assertIterableEquals(expectedOrders, resultOrders);
    }

    @Test
    void saveOrder() {
        when(mapperForOrder.toOrder(orderDtos.get(0))).thenReturn(orders.get(0));
        when(tariffRepo.findById(orderDtos.get(0).getTariffId())).thenReturn(Optional.of(tariff));
        when(tariffRepo.save(tariff)).thenReturn(tariff);
        when(orderRepo.save(orders.get(0))).thenReturn(orders.get(0));

        var result = orderService.saveOrder(orderDtos.get(0));

        assertNotNull(result);
        assertEquals(orders.get(0), result);
    }

    @Test
    void getOrderHistoryByCar() {
        Order expectedOrder = orders.get(0);

        when(orderRepo.findAllByCarIdAndStartDateTimeBetween(1, startDateTime, endDateTime)).
                thenReturn(List.of(orders.get(0)));

        var resultOrders = orderService.getOrderHistoryByCar(1, startDate, endDate);

        verify(orderRepo).findAllByCarIdAndStartDateTimeBetween(1, startDateTime, endDateTime);
        assertNotNull(resultOrders);
        assertEquals(expectedOrder, resultOrders.get(0));
    }
}