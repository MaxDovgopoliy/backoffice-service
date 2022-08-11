package com.service.backoffice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffDTO {

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
