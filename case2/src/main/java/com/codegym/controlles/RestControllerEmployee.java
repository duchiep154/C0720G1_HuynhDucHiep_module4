package com.codegym.controlles;

import com.codegym.entity.employee.Employee;
import com.codegym.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employeeListRest")
public class RestControllerEmployee {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getListEmployee() {
        List<Employee> listEmployee = this.employeeService.allEmployee();
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<List<Employee>> createNewEmployee(@RequestBody Employee employee) {
        System.out.println(employee.getName());
        this.employeeService.save(employee);
        List<Employee> listEmployee = this.employeeService.allEmployee();
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }
}
