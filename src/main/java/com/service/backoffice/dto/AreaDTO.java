package com.service.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    @NotNull
    private String country;
    @NotNull
    private String city;
    @Size(min=3)
    List<CoordinatesDTO> coordinatesDTOList;
}
