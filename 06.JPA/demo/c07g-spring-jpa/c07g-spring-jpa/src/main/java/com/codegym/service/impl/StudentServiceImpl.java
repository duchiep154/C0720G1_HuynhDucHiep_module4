package com.codegym.service.impl;

import com.codegym.entity.Student;
import com.codegym.repository.StudentRepository;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
//        return this.studentRepository.findAll();
//        return this.studentRepository.findByNameContaining("T");
        return this.studentRepository.findByNameContainingAndGenderContaining("T", "3");
    }

    @Override
    public Student findById(Integer id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }
}
