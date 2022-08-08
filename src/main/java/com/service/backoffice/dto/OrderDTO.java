package com.service.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int prise;
    private int carId;
    private String carType;
    private int userId;

}
