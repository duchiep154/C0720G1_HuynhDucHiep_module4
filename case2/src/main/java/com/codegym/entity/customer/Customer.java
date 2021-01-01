package com.codegym.entity.customer;

import com.codegym.entity.contract.Contract;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.common.MyGenerator")
    @Column(name="id")
    private String id;

    @NotBlank(message = "Please input name !")
    private String name;

    @NotBlank(message = "Please input birth day !")
    private String birthDay;

    private String gender;

    @NotBlank(message = "Please input ID card !")
    @Pattern(regexp = "^(\\d{9})|(\\d{12})$", message = "Invalid ID card ! Format ID Card is " +
            "XXXXXXXXX or XXXXXXXXXXXX with X is number from 0 to 9 !")
    private String idCard;

    @NotBlank(message = "Please input phone number !")
    @Pattern(regexp = "^(090|091|\\(84\\)(\\+90|\\+91))(\\d{7})$", message = "Invalid phone number ! Format phone " +
            "number is 090xxxxxxx or 091xxxxxxx or (84)+90xxxxxxx or " +
            "(84)+91xxxxxxx with x is number from 0 to 9 !")
    private String phone;

    @NotBlank(message = "Please input email !")
    @Email(message = "Invalid email ! Format email is abc@abc.abc !")
    private String email;

    @NotBlank(message = "Please input address !")
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_type_id", referencedColumnName = "id")
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Contract> contractList;

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}
