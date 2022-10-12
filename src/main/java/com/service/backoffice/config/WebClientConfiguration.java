package com.service.backoffice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${user.service.url}")
    private String basicUrlUser;

    @Bean
    public WebClient userWebClient() {
        return WebClient.builder()
                .baseUrl(basicUrlUser)
                .build();
    }

}

