package com.service.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.backoffice.validation.ValidOrder;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
@ValidOrder
@Data
@NoArgsConstructor
public class OrderDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private int prise;

    private int carId;

    private String carType;

    private int userId;

    public OrderDto(LocalDateTime startDate, LocalDateTime endDate, int prise,
                    int carId, String carType, int userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.prise = prise;
        this.carId = carId;
        this.carType = carType;
        this.userId = userId;
    }
}
