package com.service.backoffice.controller.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.dto.OrderDTO;
import com.service.backoffice.dto.TariffDTO;
import com.service.backoffice.services.OrderService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    OrderController orderController;
    @MockBean
    OrderService orderService;
//    LocalDateTime startDate;
//    private LocalDateTime endDate;
//    private int prise;
//    private int carId;
//    private String carType;
//    private int userId;
    @Test
    void getAllOrdersHistory() throws Exception {

        OrderDTO orderDTO1= new OrderDTO(LocalDateTime.of(2020, 1, 1, 0, 0,0),
                LocalDateTime.now(),250,1,"sedan",2);
        OrderDTO orderDTO2= new OrderDTO(LocalDateTime.of(2020, 3, 1, 0, 0,0),
                LocalDateTime.now(),240,4,"moto",3);

        List<OrderDTO> orders= List.of(orderDTO1,orderDTO2);

        given(orderService.getAllOrderHistory(null,null,null)).willReturn(orders);


        mockMvc.perform(get("/manager/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(orders.size())))
                .andExpect(jsonPath("$[0].startDate").value(orders.get(0).getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .andExpect(jsonPath("$[0].endDate").value(orders.get(0).getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                .andExpect(jsonPath("$[0].carType").value(orders.get(0).getCarType()))
                .andExpect(jsonPath("$[0].prise").value(orders.get(0).getPrise()))
                .andExpect(jsonPath("$[0].carId").value(orders.get(0).getCarId()))
        ;
    }

}