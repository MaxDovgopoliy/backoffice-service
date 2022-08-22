package com.service.backoffice.services.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.OrderDto;
import com.service.backoffice.mapper.OrderMapper;
import com.service.backoffice.repositories.OrderRepo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceImplTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderServiceImpl orderService;
    @MockBean
    OrderRepo orderRepo;


    private List<OrderDto> orderDtos = List.of(
            new OrderDto(LocalDateTime.of(2020, 1, 1, 0, 0,0),
            LocalDateTime.now(),250,1,"sedan",2),

            new OrderDto(LocalDateTime.of(2020, 3, 1, 0, 0,0),
            LocalDateTime.now(),240,4,"moto",3));
    private LocalDateTime startDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0);
    private LocalDate startDate = LocalDate.of(1900, 1, 1);
    private LocalDate endDate = LocalDate.of(2021, 1, 1);
    private LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

    @Test
    void getOrderHistoryByUser() {
        when(orderRepo.findAllByUserIdAndStartDateTimeBetween(2,startDateTime,endDateTime)).
                thenReturn(List.of(OrderMapper.MAPPER.toOrder(orderDtos.get(0))));

        List<OrderDto> resultOrderDtos =orderService.getOrderHistoryByUser(2,startDate,endDate,"sedan");

        verify(orderRepo).findAllByUserIdAndStartDateTimeBetween(2,startDateTime,endDateTime);
        assertNotNull(resultOrderDtos);
        assertEquals(orderDtos.get(0), resultOrderDtos.get(0));
    }
@Test
    void getOrderHistoryByUserWithoutAdditionParams() {
        when(orderRepo.
                findAllByUserIdAndStartDateTimeBetween(anyInt(),any(LocalDateTime.class),any(LocalDateTime.class))).
                thenReturn(List.of(OrderMapper.MAPPER.toOrder(orderDtos.get(0))));

        List<OrderDto> resultOrderDtos =orderService.getOrderHistoryByUser(2,null,null,null);

        verify(orderRepo).findAllByUserIdAndStartDateTimeBetween(anyInt(),any(LocalDateTime.class),any(LocalDateTime.class));
        assertNotNull(resultOrderDtos);
        assertEquals(orderDtos.get(0), resultOrderDtos.get(0));
    }

    @Test
    void getAllOrderHistory() {
        List<OrderDto> expectedOrdersDto=List.of(orderDtos.get(0));
        when(orderRepo.findAllByStartDateTimeBetween(startDateTime,endDateTime)).thenReturn(OrderMapper.MAPPER.toOrders(
                orderDtos));

        List<OrderDto> resultOrderDtos =orderService.getAllOrderHistory(startDate,endDate,"sedan");

        verify(orderRepo).findAllByStartDateTimeBetween(startDateTime,endDateTime);
        assertNotNull(resultOrderDtos);
        assertIterableEquals(expectedOrdersDto, resultOrderDtos);
    }
    @Test
    void getAllOrderHistoryWithoutAdditionParams() {
        List<OrderDto> expectedOrdersDto=orderDtos;
        when(orderRepo.findAllByStartDateTimeBetween(any(LocalDateTime.class),any(LocalDateTime.class))).
                thenReturn(OrderMapper.MAPPER.toOrders(orderDtos));

        List<OrderDto> resultOrderDtos =orderService.getAllOrderHistory(null,null,null);

        verify(orderRepo).findAllByStartDateTimeBetween(any(LocalDateTime.class),any(LocalDateTime.class));
        assertNotNull(resultOrderDtos);
        assertIterableEquals(expectedOrdersDto, resultOrderDtos);
    }
}