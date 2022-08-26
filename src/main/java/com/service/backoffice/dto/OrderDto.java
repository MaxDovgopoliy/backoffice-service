package com.service.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.backoffice.validation.order.ValidOrder;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValidOrder
@Data
@NoArgsConstructor
public class OrderDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;

    private int prise;

    private int carId;

    private String carType;

    private int userId;

    public OrderDto(LocalDateTime startDateTime, LocalDateTime endDateTime, int prise,
                    int carId, String carType, int userId) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.prise = prise;
        this.carId = carId;
        this.carType = carType;
        this.userId = userId;
    }
}
