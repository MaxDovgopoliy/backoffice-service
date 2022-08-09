package com.service.backoffice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
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
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
//    public LocalDateTime getStartDate() {
//        return startDate;
//    }
}
