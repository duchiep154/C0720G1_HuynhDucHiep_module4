package com.codegym.service.employee;

import com.codegym.entity.employee.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByNameContaining(Pageable pageable, String name);

    void deleteEmployee (Integer id);

    void save(Employee employee);

    void update(Employee employee);

    List<Employee> allEmployee();

    List<String> allNameEmployee();

    List<Position> allPosition();

    List<Division> allDivision();

    List<EducationDegree> allEducationDegree();

    List<AppUser> allUser();

    boolean testRole(AppUser appUser, Long id);

    String saveUser(AppUser appUser, Long id, String verification);
}
