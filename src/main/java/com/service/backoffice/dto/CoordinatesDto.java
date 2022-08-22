package com.service.backoffice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    @NotNull
    @Range(min = -90, max = 90, message = "should be a coordinate")
    private double latitude;

    @NotNull
    @Range(min = -180, max = 180, message = "should be a coordinate")
    private double longitude;
}
