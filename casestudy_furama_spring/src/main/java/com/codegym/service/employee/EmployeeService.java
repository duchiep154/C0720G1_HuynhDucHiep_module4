package com.codegym.service.employee;

import com.codegym.entity.employee.Division;
import com.codegym.entity.employee.EducationDegree;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.employee.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Page<Employee> findAll(Pageable pageable);

    Employee findById(String id);

    void save(Employee employee);

    void update(Employee employee);

    void remove(String id);

    List<Division> findAllDivision();

    List<EducationDegree> findAllEducationDegree();

    List<Position> findAllPosition();

    Page<Employee> findByNameContaining (Pageable pageable, String name);
}
