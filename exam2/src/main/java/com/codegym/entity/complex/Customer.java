package com.codegym.entity.complex;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idCustomer;

    private String name;

    private String phone;

    private String email;



    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<GiaoDich> giaoDichList;

    public Customer() {
    }

    public Integer getId() {
        return idCustomer;
    }

    public void setId(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GiaoDich> getGiaoDichList() {
        return giaoDichList;
    }

    public void setGiaoDichList(List<GiaoDich> giaoDichList) {
        this.giaoDichList = giaoDichList;
    }
}
