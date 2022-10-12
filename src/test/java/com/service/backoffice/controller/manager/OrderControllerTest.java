package com.service.backoffice.controller.manager;

import com.service.backoffice.mapper.MapperForOrder;
import com.service.backoffice.services.OrderService;
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
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MapperForOrder mapperForOrder;

    @Autowired
    OrderController orderController;
    @MockBean
    OrderService orderService;

    @Test
    void getAllOrdersHistory() throws Exception {

//        OrderDto orderDto1 = new OrderDto(LocalDateTime.of(2020, 1, 1, 0, 0,0),
//                LocalDateTime.now(),new BigDecimal(250),1,"sedan",2,2);
//        OrderDto orderDto2 = new OrderDto(LocalDateTime.of(2020, 3, 1, 0, 0,0),
//                LocalDateTime.now(),new BigDecimal(240),4,"moto",3,2);
//
//        List<OrderDto> orderDtos= List.of(orderDto1, orderDto2);
//        List<Order> orders= mapperForOrder.toOrders(orderDtos);
//
//        given(orderService.getAllOrderHistory(null,null,null)).willReturn(orders);
//
//
//        mockMvc.perform(get("/manager/orders")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(orderDtos.size())))
//                .andExpect(jsonPath("$[0].startDateTime").value(orderDtos.get(0).getStartDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
//                .andExpect(jsonPath("$[0].endDateTime").value(orderDtos.get(0).getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
//                .andExpect(jsonPath("$[0].carType").value(orderDtos.get(0).getCarType()))
//                .andExpect(jsonPath("$[0].price").value(orderDtos.get(0).getPrice()))
//                .andExpect(jsonPath("$[0].carId").value(orderDtos.get(0).getCarId()))
//        ;
    }

}