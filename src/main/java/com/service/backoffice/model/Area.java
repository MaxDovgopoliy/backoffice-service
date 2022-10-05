package com.service.backoffice.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "areaId", referencedColumnName = "id")
    private List<Coordinates> coordinates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    public Area(City city) {
        this.city = city;
    }
}
