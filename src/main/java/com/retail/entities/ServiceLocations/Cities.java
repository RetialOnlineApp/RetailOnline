package com.retail.entities.ServiceLocations;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String city;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Areas> areas;

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(List<Areas> areas) {
        this.areas = areas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
