package com.codegym.entity;

import com.codegym.common.CheckBirthday;
import com.codegym.common.CheckEmail;
import com.codegym.common.CheckName;
import com.codegym.common.CheckPhone;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "user")
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotBlank(message = "Do Not Blank")
//    @Size(min = 2, max = 45,message = "Input 5 - 45 character")
    @CheckName  // custom annotation validate
    private String firstName;

//    @NotBlank(message = "Do Not Blank")
//    @Size(min = 2, max = 45,message = "Input 5 - 45 character")
    @CheckName
    private String lastName;
//   su dung validate binh thuong
//    @Pattern(regexp = "^0\\d{9}$", message = "Wrong Format - 0XXXXXXXXX\n with X: 0-9")
    @CheckPhone
    private String phoneNumber;
//    su dung validate binh thuong
//    @Email(message = "Wrong Format \n vd: abc@abc.com.vn")
    @CheckEmail
    private String email;

   @CheckBirthday
    private String dayOfBirth;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
//        User user = (User) target;
//
//        if (user.firstName.equals("") && user.lastName.equals("")) {
//            errors.rejectValue("name", "name.blank");
//        }
//        if (user.firstName.length()<=2 && user.lastName.length()){
//
//        }
    }
}