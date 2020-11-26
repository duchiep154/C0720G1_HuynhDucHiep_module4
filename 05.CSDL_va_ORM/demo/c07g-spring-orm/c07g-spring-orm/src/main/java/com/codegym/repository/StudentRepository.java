package com.codegym.repository;

import com.codegym.entity.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> findAll();

    Student findById(Integer id);

    void save(Student student);
}
