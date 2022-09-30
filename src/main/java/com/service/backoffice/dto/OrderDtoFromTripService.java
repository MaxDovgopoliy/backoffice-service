package com.service.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.backoffice.validation.order.ValidOrder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValidOrder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoFromTripService {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;

    private BigDecimal price;

    private int carId;

    private int ratePerHour;

    private int userId;

}
