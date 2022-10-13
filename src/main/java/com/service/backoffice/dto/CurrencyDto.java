package com.service.backoffice.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CurrencyDto {
    private String table;
    private final Map<String, Double> rates = new HashMap<>();
    private String lastupdate;
}
