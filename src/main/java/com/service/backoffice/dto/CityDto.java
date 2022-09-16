package com.service.backoffice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    @Pattern(regexp = "[a-z-A-Z]{2,}",message = "incorrect city name")
    private String name;
    @Min(value = 0)
    private double square;
    @Pattern(regexp = "[a-z-A-Z]{2,}",message = "incorrect country name")
    private String countryName;
}
