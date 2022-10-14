package com.service.backoffice.util;

import com.service.backoffice.dto.CurrencyDto;
import org.springframework.web.reactive.function.client.WebClient;

public class CurrencyUtil {
    public static double getCurrentRateToUsd(String from, String to, double amount) {
        WebClient userServiceClient =
                WebClient.create("https://cdn.moneyconvert.net/api/latest.json");

        CurrencyDto currencyDto = userServiceClient
                .get()
                .retrieve()
                .bodyToMono(CurrencyDto.class)
                .block();

        amount = amount / currencyDto.getRates().get(from) * currencyDto.getRates().get(to);

        return amount;
    }
}
