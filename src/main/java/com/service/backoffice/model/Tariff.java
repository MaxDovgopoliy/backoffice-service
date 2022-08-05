package com.service.backoffice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String carType;
    private int ratePerHour;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name ="tariffId",referencedColumnName = "id")
    List<Order> orders= new ArrayList<>();

    public Tariff(String name, String description, String carType, int ratePerHour) {
        this.name = name;
        this.description = description;
        this.carType = carType;
        this.ratePerHour = ratePerHour;
    }
    public Tariff(Long id,String name, String description, String carType, int ratePerHour) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.carType = carType;
        this.ratePerHour = ratePerHour;
    }
    public Tariff(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(int ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
