package com.codegym.entity.employee;

import javax.persistence.*;
import java.util.List;

@Entity(name = "education_degree")
public class EducationDegree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "educationDegree", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    public EducationDegree() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
