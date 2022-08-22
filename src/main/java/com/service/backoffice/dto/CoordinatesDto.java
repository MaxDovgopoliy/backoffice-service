package com.service.backoffice.dto;

import com.sun.istack.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    @NotNull
    @Pattern(regexp = "-?[1-9][0-9]*(\\.[0-9]+)?", message = "should be a coordinate")
    private double latitude;

    @NotNull
    @Pattern(regexp = "-?[1-9][0-9]*(\\.[0-9]+)?", message = "should be a coordinate")
    private double longitude;
}
