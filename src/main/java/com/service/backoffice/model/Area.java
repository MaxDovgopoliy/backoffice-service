package com.service.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double square;

    private String address;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    public Area(double square, String address, City city) {
        this.square = square;
        this.address = address;
        this.city = city;
    }
}
