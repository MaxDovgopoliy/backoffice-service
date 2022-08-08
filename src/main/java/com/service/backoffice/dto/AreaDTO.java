package com.service.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDTO {
    private String country;
    private String city;
    List<CoordinatesDTO> coordinatesDTOList;
}
