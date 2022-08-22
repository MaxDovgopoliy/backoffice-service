package com.service.backoffice.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*")
    private String country;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]*")
    private String city;
    @Size(min = 3, message = "should be at least three coordinates")
    private List<CoordinatesDto> coordinatesDtoList;

}
