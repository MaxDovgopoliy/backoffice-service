package com.service.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private Area area;

    public Coordinates(Long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates() {
    }

}
