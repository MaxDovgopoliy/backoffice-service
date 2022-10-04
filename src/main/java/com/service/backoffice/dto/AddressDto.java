package com.service.backoffice.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @Pattern(regexp = "[A-Z][a-z]*-?[a-z]", message = "incorrect street")
    private String street;
    @Positive(message = "number must be greater than 0")
    private int number;

    private long areaId;
}
