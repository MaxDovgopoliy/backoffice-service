package com.service.backoffice.controller.user;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.service.backoffice.util.security.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@AutoConfigureMockMvc
@SpringBootTest
@Testcontainers
class UserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityUtil securityUtil;
    private static final MySQLContainer<?> container;

    static {
        container = new MySQLContainer<>("mysql:latest");
        container.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.flyway.url", container::getJdbcUrl);
        registry.add("spring.flyway.username", container::getUsername);
        registry.add("spring.flyway.password", container::getPassword);
    }

    @Test
    void getAllTariffs() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("http://localhost:8080/user/tariffs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("countryName","Ukraine")
                        .param("cityName","Lviv")
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrdersHistoryByUser() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrdersHistoryByCar() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/cars/1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAreas() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/areas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCountries() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCities() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void getTariffByCarType() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/tariffs/medium")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("latitude","1")
                        .param("longitude","1")
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void validateParkingCoordinates() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("/user/validate-coordinates-for-parking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("latitude","1")
                        .param("longitude","1")
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }
}