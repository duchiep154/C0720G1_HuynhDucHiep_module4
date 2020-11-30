package com.example.service;

import com.example.entity.Student;

public interface StudentService {
    void save(Student student);
    Student findById(Long id);
}
