package com.codegym.service.impl;

import com.codegym.entity.Student;
import com.codegym.repository.StudentRepository;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private static Map<Integer, Student> studentMap;

    static {
        studentMap = new TreeMap<>();
        studentMap.put(242, new Student(242, "Din", "14/10/2020", 5, 1));
        studentMap.put(742, new Student(742, "Din1", "14/11/2020", 4, 2));
        studentMap.put(142, new Student(142, "Din2", "14/12/2020", 9, 0));
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }
}
