package com.service.backoffice.dto;

import com.service.backoffice.validation.area.ValidArea;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValidArea
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {
    @NotNull(message = "address must not be null")
    private AddressDto address;

    @Positive(message = "square must be greater than 0")
    private double square;

    private String countryName;

    private String cityName;

}
