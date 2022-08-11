package com.service.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.backoffice.validation.ValidOrder;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class OrderDTO {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private int prise;

    private int carId;

    private String carType;

    private int userId;
    @ValidOrder
    public OrderDTO(LocalDateTime startDate, LocalDateTime endDate, int prise, int carId, String carType, int userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.prise = prise;
        this.carId = carId;
        this.carType = carType;
        this.userId = userId;
    }
}
