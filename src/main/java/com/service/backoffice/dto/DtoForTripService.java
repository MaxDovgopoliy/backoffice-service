package com.service.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoForTripService {
    private long tariffId;
    private String countryName;
    private String cityName;
    private String unitOfSpeed;
    private String currency;
    private double ratePerHour;
}
