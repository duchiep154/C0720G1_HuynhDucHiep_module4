package com.codegym.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "provincial")
public class Provincial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "provincial", cascade = CascadeType.ALL)
    private List<Customer> customerList;

    public Provincial() {
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

    @Override
    public String toString() {
        return "Provincial{" +
                "name='" + name + '\'' +
                '}';
    }
}

