package com.service.backoffice.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
}
