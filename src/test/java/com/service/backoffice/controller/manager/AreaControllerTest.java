package com.service.backoffice.controller.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.services.implementation.TariffServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AreaControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AreaController AreaController;

    @MockBean
    AreaService areaService;

    @Test
    void deleteArea() {
    }

    @Test
    void addArea() {
    }

    @Test
    void updateArea() {
    }

    @Test
    void getAreaById() {
    }
}