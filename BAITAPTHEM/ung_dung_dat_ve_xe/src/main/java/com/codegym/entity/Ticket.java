package com.codegym.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer id;


    @ManyToMany(mappedBy = "customerTicket")
    @JsonBackReference
    private List<Customer> customerList;

    private String timeDeparture;
    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "ticket")

    private Province provinceDeparture;
    @ManyToOne
    @JoinColumn(name = "province_id",referencedColumnName = "ticket")
    private Province provinceArrival;

    public Ticket() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public Province getProvinceDeparture() {
        return provinceDeparture;
    }

    public void setProvinceDeparture(Province provinceDeparture) {
        this.provinceDeparture = provinceDeparture;
    }

    public Province getProvinceArrival() {
        return provinceArrival;
    }

    public void setProvinceArrival(Province provinceArrival) {
        this.provinceArrival = provinceArrival;
    }
}

