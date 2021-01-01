package com.codegym.entity.customer;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.login.AppUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
//    @Id
//    @NotBlank(message = "Do Not Blank")
//    @Pattern(regexp = "^(KH-)\\d{4}$", message = "Invalid ID ! Format: KH-XXXX (X: 0- 9)")
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.common.MyGenerator")
    @Column(name="id")
    private String id;

    @OneToOne
    @JoinColumn(name = "user_info_customer_id", referencedColumnName = "user_id")
    private AppUser appUser;

    @NotBlank(message = "Do Not Blank")
    private String fullName;

    @NotBlank(message = "Do Not Blank")
    private String birthday;

    @NotBlank(message = "Do Not Blank")
    private String gender;

    @Pattern(regexp = "^(\\d{9})|(\\d{12})$",
            message = "Invalid ID card ! Format: XXXXXXXXX or XXXXXXXXXXXX (X: 0-9)")
    private String idCard;

    @Pattern(regexp = "^(090|091|\\(84\\)(\\+90|\\+91))(\\d{7})$",
            message = "Invalid Phone ! Format:  090xxxxxxx || 091xxxxxxx || (84)+90xxxxxxx || (84)+91xxxxxxx (x: 0-9)")
    private String phone;

    @NotBlank(message = "Do Not Blank")
    @Email(message = "Invalid Email ! Example: abc@abc.com.vn")
    private String email;

    @NotBlank(message = "Do Not Blank")
    private String address;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //    ------------------------------------ Tạo Mối Quan Hệ ---------------------------------------------
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contract> contractList;

    @ManyToOne
    @JoinColumn(name = "type_customer_id", referencedColumnName = "id")
    private TypeCustomer typeCustomer;
//    ---------------------------------------------------------------------------------------------------

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public TypeCustomer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(TypeCustomer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    @Override
    public String toString() {
         return "Customer [id=" + id + "]";
    }
}
