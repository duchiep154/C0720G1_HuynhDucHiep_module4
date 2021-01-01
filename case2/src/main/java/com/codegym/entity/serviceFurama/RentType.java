package com.codegym.entity.serviceFurama;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class RentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double cost;

    @OneToMany(mappedBy = "rentType", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ServiceFurama> serviceFuramaList;

    public RentType() {
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<ServiceFurama> getServiceFuramaList() {
        return serviceFuramaList;
    }

    public void setServiceFuramaList(List<ServiceFurama> serviceFuramaList) {
        this.serviceFuramaList = serviceFuramaList;
    }
}
