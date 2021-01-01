//package com.codegym.entity.login;
//
//
//import com.codegym.entity.customer.Customer;
//import com.codegym.entity.employee.Employee;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import javax.validation.constraints.AssertTrue;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class RegisterUser {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "reg_id", nullable = false)
//
//    private Long regId;
//
//
//
//
//
//    @NotBlank(message = "Do Not Blank")
//    private String fullName;
//
//    @NotBlank(message = "Do Not Blank")
//    private String birthday;
//
//    @NotBlank(message = "Do Not Blank")
//    private String gender;
//
//    @Pattern(regexp = "^(\\d{9})|(\\d{12})$",
//            message = "Invalid ID card ! Format: XXXXXXXXX or XXXXXXXXXXXX (X: 0-9)")
//    private String idCard;
//
//    @Pattern(regexp = "^(090|091|\\(84\\)(\\+90|\\+91))(\\d{7})$",
//            message = "Invalid Phone ! Format:  090xxxxxxx || 091xxxxxxx || (84)+90xxxxxxx || (84)+91xxxxxxx (x: 0-9)")
//    private String phone;
//
//    @NotBlank(message = "Do Not Blank")
//    @Email(message = "Invalid Email ! Example: abc@abc.com.vn")
//    private String email;
//
//    @NotBlank(message = "Do Not Blank")
//    private String address;
//
//    @OneToOne
//
//    private Customer customer;
//
//    @OneToOne
//    private Employee employee;
//
//
//
//
//
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(String birthday) {
//        this.birthday = birthday;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getIdCard() {
//        return idCard;
//    }
//
//    public void setIdCard(String idCard) {
//        this.idCard = idCard;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//
//
//
//}
