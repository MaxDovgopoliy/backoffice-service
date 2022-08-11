package com.service.backoffice.model;





import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Area() {
    }

    public Area(Long id, String country, String city, List<Coordinates> listOfCoordinates) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.listOfCoordinates = listOfCoordinates;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return country.equals(area.country) && city.equals(area.city) && listOfCoordinates.equals(area.listOfCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, listOfCoordinates);
    }
}
