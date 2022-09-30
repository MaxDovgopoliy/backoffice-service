package com.service.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.backoffice.validation.order.ValidOrder;
import java.math.BigDecimal;
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

    private BigDecimal price;

    private int carId;

    private String carType;

    private int userId;

    private long tariffId;

    public OrderDto(LocalDateTime startDateTime, LocalDateTime endDateTime, BigDecimal price,
                    int carId, String carType, int userId, long tariffId) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.price = price;
        this.carId = carId;
        this.carType = carType;
        this.userId = userId;
        this.tariffId = tariffId;
    }

    public long getTariffId() {
        return tariffId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }
}
