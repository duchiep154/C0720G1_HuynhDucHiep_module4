package com.codegym.entity.serviceFurama;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ServiceFurama> serviceFuramaList;

    public ServiceType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceFurama> getServiceFuramaList() {
        return serviceFuramaList;
    }

    public void setServiceFuramaList(List<ServiceFurama> serviceFuramaList) {
        this.serviceFuramaList = serviceFuramaList;
    }
}
