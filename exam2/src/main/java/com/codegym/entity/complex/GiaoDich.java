package com.codegym.entity.complex;


import javafx.util.converter.LocalDateStringConverter;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForLocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.Validation;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name="giaodich")
@Data
public class GiaoDich  {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.comon.MyGenerator")
    @Column(name="id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "ngay phai hon ngay hien tai !")
    private Date ngayGD;

    @ManyToOne
    @JoinColumn(name = "loaidv_id", referencedColumnName = "id_loai_dv")
    private LoaiDV loaiDV;


    @Positive
    @Min(value = 500000,message = "don gia >= 500.000")
    private long donGia;

     @Positive
     @Min(value = 20, message = "dien tich phai hon 20")
    private long dienTich;


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

    public Date getNgayGD() {
        return ngayGD;
    }

    public void setNgayGD(Date ngayGD) {
        this.ngayGD = ngayGD;
    }

    public LoaiDV getLoaiDV() {
        return loaiDV;
    }

    public void setLoaiDV(LoaiDV loaiDV) {
        this.loaiDV = loaiDV;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getDienTich() {
        return dienTich;
    }

    public void setDienTich(long dienTich) {
        this.dienTich = dienTich;
    }

}
