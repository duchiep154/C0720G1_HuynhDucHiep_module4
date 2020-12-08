package com.codegym.controller;

import com.codegym.entity.Student;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/apiStudent")
@CrossOrigin
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getAllStudent() {

        List<Student> studentList = this.studentService.findAll();

        if (studentList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Student> getDetailStudent(@PathVariable Integer id) throws Exception {
        Student student = this.studentService.findById(id);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        this.studentService.save(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/apiStudent/detail/{id}").buildAndExpand(student.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
