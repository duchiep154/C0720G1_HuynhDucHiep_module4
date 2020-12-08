package com.codegym.entity;

import javax.persistence.*;

@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "date_of_birth", length = 50)
    private String dateOfBirth;
    private double mark;
    private int gender;

    public Student() {

    }

    public Student(Integer id, String name, String dateOfBirth, double mark, int gender) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.mark = mark;
        this.gender = gender;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
