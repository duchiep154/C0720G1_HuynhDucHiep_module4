package com.codegym.service;

import com.codegym.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Page<Student> findAll(Pageable pageable);

    Student findById(Integer id) throws Exception;

    void save(Student student);

    Page<Student> searchStudentByName(String keyword, Pageable pageable);
}
