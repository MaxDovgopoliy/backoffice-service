package com.service.backoffice.controller.manager;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.backoffice.dto.TariffDto;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.util.security.SecurityUtil;
import java.util.Set;
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
class TariffControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
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
    void addTariff() throws Exception {
        TariffDto tariffDtoForAdd = new TariffDto("tariff",
                "good one",
                "medium",
                "UAH",
                100,
                Set.of("Lviv"));
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(post("http://localhost:8089/manager/tariffs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tariffDtoForAdd))
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTariff() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(delete("http://localhost:8089/manager/tariffs/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

//    @Sql(value = "/add_tariff.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void getTariff() throws Exception {
        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(get("http://localhost:8089/manager/tariffs/3")
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTariff() throws Exception {
        Tariff tariffDtoForUpdate = new Tariff("work",
                "good for work trips",
                "business",
                260,"UAH");

        doAnswer((i)-> null).when(securityUtil).tokenCheckForRole(anyString(),anySet());

        mockMvc.perform(put("http://localhost:8089/manager/tariffs/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tariffDtoForUpdate))
                        .header("Authorization", "Test"))
                .andExpect(status().isOk());
    }
}