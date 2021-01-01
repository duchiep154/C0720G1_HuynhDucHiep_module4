package com.codegym.entity.complex;


import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity
@Table(name="giaodich")
@Data
public class GiaoDich implements Serializable {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.common.MyGenerator")
    @Column(name="id")
    private String id;


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @NotBlank(message = "nhap ngay giao dich !")
    @FutureOrPresent(message = "ngay phai hon ngay hien tai !")
    private String ngayGD;


    @ManyToOne
    @JoinColumn(name = "loaidv_id",referencedColumnName = "id")
    private  LoaiDV loaiDV;




    @NotBlank(message = "Do Not Blank")
    @Min(value = 500000,message = "don gia >= 500.000")
    private Double donGia;

     @NotBlank
     @Min(value = 20, message = "dien tich phai hon 20")
    private Double dienTich;


    public GiaoDich() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(String ngayGD) {
        this.ngayGD = ngayGD;
    }

    public LoaiDV getLoaiDV() {
        return loaiDV;
    }

    public void setLoaiDV(LoaiDV loaiDV) {
        this.loaiDV = loaiDV;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }


}
