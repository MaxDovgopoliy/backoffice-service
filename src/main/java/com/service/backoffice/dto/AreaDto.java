package com.service.backoffice.dto;

import com.service.backoffice.validation.area.ValidArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValidArea
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {

    private String address;

    private double square;

    private String countryName;

    private String cityName;

}
