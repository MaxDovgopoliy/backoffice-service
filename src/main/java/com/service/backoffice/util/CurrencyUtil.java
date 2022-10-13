package com.service.backoffice.util;

import com.service.backoffice.dto.CurrencyDto;
import org.springframework.web.reactive.function.client.WebClient;

public class CurrencyUtil {
    public static double getCurrentRateToUsd(String currency) {
        WebClient userServiceClient =
                WebClient.create("https://cdn.moneyconvert.net/api/latest.json");

        CurrencyDto currencyDto = userServiceClient
                .get()
                .retrieve()
                .bodyToMono(CurrencyDto.class)
                .block();
        return currencyDto.getRates().get(currency);
    }
}
