package com.service.backoffice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffDto {

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotNull
    @Size(min = 5, message = "description should be longer")
    private String description;
    @NotNull
    private String carType;
    @NotNull
    @Positive(message = "rate Per Hour should be greater than 0")
    private int ratePerHour;

}
