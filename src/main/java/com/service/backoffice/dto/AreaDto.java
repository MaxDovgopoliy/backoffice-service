package com.service.backoffice.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {

    @NotNull
    private String country;
    @NotNull
    private String city;
    @Size(min = 3)
    private List<CoordinatesDto> coordinatesDtoList;

}