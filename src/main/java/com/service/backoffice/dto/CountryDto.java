package com.service.backoffice.dto;

import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    @Pattern(regexp = "[a-z-A-Z]{2,}",message = "incorrect country name")
    private String name;
}
