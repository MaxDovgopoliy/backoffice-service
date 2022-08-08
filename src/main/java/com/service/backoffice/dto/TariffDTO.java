package com.service.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffDTO {

    private String name;
    private String description;
    private String carType;
    private int ratePerHour;

}
