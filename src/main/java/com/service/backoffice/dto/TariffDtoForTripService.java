package com.service.backoffice.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffDtoForTripService {
    private long id;
    private String name;
    private String description;
    private String carType;

    private String currency;

    private double ratePerHour;

    private Set<String> cities;
}
