package com.service.backoffice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String country;
    private String city;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name ="areaId",referencedColumnName = "id")
    private List<Coordinates> listOfCoordinates= new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<Coordinates> getListOfCoordinates() {
        return listOfCoordinates;
    }

    public void setListOfCoordinates(List<Coordinates> listOfCoordinates) {
        this.listOfCoordinates = listOfCoordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
