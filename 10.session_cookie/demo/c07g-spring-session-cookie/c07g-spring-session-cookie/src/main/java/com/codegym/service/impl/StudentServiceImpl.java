package com.codegym.service.impl;

import com.codegym.entity.Student;
import com.codegym.repository.StudentRepository;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
//        return this.studentRepository.findByNameContaining("T");
//        return this.studentRepository.findByNameContainingAndGenderContaining("T", "3");
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(Integer id) throws Exception {
        Student student = this.studentRepository.findById(id).orElse(null);

        if (student == null) {
            throw new Exception("Hoc vien nay hien khong co trong database.");
        }

        return student;
    }

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public Page<Student> searchStudentByName(String keyword, Pageable pageable) {
        return this.studentRepository.findByNameContaining(keyword, pageable);
    }
}
