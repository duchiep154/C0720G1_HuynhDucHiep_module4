package com.codegym.service.employee.impl;

import com.codegym.entity.employee.Division;
import com.codegym.entity.employee.EducationDegree;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.employee.Position;
import com.codegym.repository.employee.DivisionRepository;
import com.codegym.repository.employee.EducationDegreeRepository;
import com.codegym.repository.employee.EmployeeRepository;
import com.codegym.repository.employee.PositionRepository;
import com.codegym.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private EducationDegreeRepository educationDegreeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findById(String id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void remove(String id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public List<Division> findAllDivision() {
        return this.divisionRepository.findAll();
    }

    @Override
    public List<EducationDegree> findAllEducationDegree() {
        return this.educationDegreeRepository.findAll();
    }

    @Override
    public List<Position> findAllPosition() {
        return this.positionRepository.findAll();
    }

    @Override
    public Page<Employee> findByNameContaining(Pageable pageable, String name) {
        return this.employeeRepository.findAllByFullNameContaining(pageable, name);
    }
}
