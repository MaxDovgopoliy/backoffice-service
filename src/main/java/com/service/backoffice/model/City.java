package com.service.backoffice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double coefficientForTariff;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private List<Area> areas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private List<Coordinates> coordinates = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cities_tariffs",
            joinColumns = {@JoinColumn(name = "city_id")},
            inverseJoinColumns = {@JoinColumn(name = "tariff_id")})
    private Set<Tariff> tariffs = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;

    public City(String name, double coefficientForTariff, List<Coordinates> coordinates,
                Country country) {
        this.name = name;
        this.coefficientForTariff = coefficientForTariff;
        this.coordinates = coordinates;
        this.country = country;
    }
}
