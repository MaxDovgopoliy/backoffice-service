package com.service.backoffice.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffDto {

    @NotNull
    private String name;
    @NotNull
    @Size(min = 5)
    private String description;
    @NotNull
    private String carType;
    @NotNull
    @Positive
    private int ratePerHour;

}
