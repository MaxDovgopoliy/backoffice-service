package com.service.backoffice.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CurrencyUtilTest {

    @Test
    void getCurrentRateToUsd() {
        double convertedRate = CurrencyUtil.convertCurrencyRate("USD", "UAH", 100);
        Assertions.assertNotNull(convertedRate);
    }
}