package com.service.backoffice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private BigDecimal price;
    private int carId;
    private String carType;
    private int userId;

    @ManyToOne
    @JoinColumn(name = "tariffId")
    private Tariff tariff;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDate) {
        this.startDateTime = startDate;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDate) {
        this.endDateTime = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal prise) {
        this.price = prise;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
