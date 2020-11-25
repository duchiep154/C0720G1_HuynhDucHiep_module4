package com.codegym.service.impl;

import com.codegym.entity.Student;
import com.codegym.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StudentServiceImpl implements StudentService {
    private static Map<Integer, Student> studentMap;

    static {
        studentMap = new TreeMap<>();
        studentMap.put(1, new Student(1, "Tu", "23/11/2020"));
        studentMap.put(2, new Student(2, "Tu2", "24/11/2020"));
        studentMap.put(3, new Student(3, "Tu3", "25/11/2020"));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public Student findById(Integer id) {
        return studentMap.get(id);
    }

    @Override
    public void save(Student student) {
        Integer id = (int) (Math.random() * 1000);
        student.setId(id);

        studentMap.put(id, student);
    }
}
