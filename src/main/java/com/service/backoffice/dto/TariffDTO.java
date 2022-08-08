package com.service.backoffice.dto;

public class TariffDTO {
    private String name;
    private String description;
    private String carType;
    private int ratePerHour;

    public TariffDTO() {
    }

    public TariffDTO(String name, String description, String carType, int ratePerHour) {
        this.name = name;
        this.description = description;
        this.carType = carType;
        this.ratePerHour = ratePerHour;
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
}
