package com.service.backoffice.dto;

import java.util.List;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    @Pattern(regexp = "[a-z-A-Z]{2,}",message = "incorrect city name")
    private String name;
    @Pattern(regexp = "[a-z-A-Z]{2,}",message = "incorrect country name")
    private String countryName;
    @Size(min = 3, message = "should be at least three coordinates")
    private List<CoordinatesDto> coordinatesDtoList;

    private double coefficientForTariff;
}
