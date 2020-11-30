package com.codegym.entity;

import javax.persistence.*;

@Entity(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String address;


    @ManyToOne
    @JoinColumn(name = "provincial_id", referencedColumnName = "id")
    private Provincial provincial;


    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Provincial getProvincial() {
        return provincial;
    }

    public void setProvincial(Provincial provincial) {
        this.provincial = provincial;
    }
}
