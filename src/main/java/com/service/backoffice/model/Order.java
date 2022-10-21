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

@Data
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private BigDecimal price;
    private String currency;
    private int carId;
    private String carType;
    private int userId;

    @ManyToOne
    @JoinColumn(name = "tariffId")
    private Tariff tariff;

    public Order() {
    }

}
