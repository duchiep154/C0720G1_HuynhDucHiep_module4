package com.codegym.entity.employee;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.login.AppUser;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity(name = "employee")
public class Employee implements Validator {
    @Id
    @GeneratedValue(generator = "my_generator3")
    @GenericGenerator(name = "my_generator3", strategy = "com.codegym.common.MyGenerator3")
    @Column(name="id")
    private String id;



//    @NotBlank(message = "Do Not Blank")
    private Float salary;



    @OneToOne
    @JoinColumn(name = "user_info_employee_id", referencedColumnName = "user_id")
    private AppUser appUser;


//    ----------------------------------- Tạo mối quan hệ ------------------------------------------------


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Contract> contractList;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "education_degree_id", referencedColumnName = "id")
    private EducationDegree educationDegree;

    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private Division division;

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

    //    -------------------------------------------------------------------------------------------------------

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EducationDegree getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(EducationDegree educationDegree) {
        this.educationDegree = educationDegree;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        if(employee.salary == null){
            errors.rejectValue("salary", "salary.empty");
        } else if (employee.salary <= 0){
            errors.rejectValue("salary","salary.positive");
        }
    }
}
