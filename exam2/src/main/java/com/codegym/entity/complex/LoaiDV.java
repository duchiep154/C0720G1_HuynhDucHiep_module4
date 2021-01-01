package com.codegym.entity.complex;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class LoaiDV implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_loai_dv")
    private Integer idLoaiDV;

    private String name;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<GiaoDich> giaoDichList;

    public LoaiDV() {
    }

    public Integer getId() {
        return idLoaiDV;
    }

    public void setId(Integer idLoaiDV) {
        this.idLoaiDV = idLoaiDV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GiaoDich> getGiaoDichList() {
        return giaoDichList;
    }

    public void setGiaoDichList(List<GiaoDich> giaoDichList) {
        this.giaoDichList = giaoDichList;
    }
}
