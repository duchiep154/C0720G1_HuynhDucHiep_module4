package com.example.controller;

import com.example.entity.Course;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Các bạn có thể tìm hiểu thêm  phần này tại blogs: https://levunguyen.com,
 * blogs chuyên về lập trình và các kỹ năng mềm trong nghề lập trình
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/registStudentToCourse")
    public void registStudentToCourse() {

        Course courseA = new Course("Course A");
        Course courseB = new Course("Course B");

        Student student = new Student("John");

        Set<Course> courses = new HashSet<>();
        courses.add(courseA);
        courses.add(courseB);

        student.setLikedCourses(courses);

        this.studentService.save(student);
    }

    @GetMapping("/getStudent/{id}")
    public Student getStudent(@PathVariable Long id) {
        return this.studentService.findById(id);
    }
}
