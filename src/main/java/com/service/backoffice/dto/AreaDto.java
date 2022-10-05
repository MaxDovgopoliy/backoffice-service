package com.service.backoffice.dto;

import com.service.backoffice.validation.area.ValidArea;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValidArea
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDto {

    private String countryName;

    private String cityName;

    @Size(min = 3)
    private List<CoordinatesDto> coordinatesDtoList;

}
