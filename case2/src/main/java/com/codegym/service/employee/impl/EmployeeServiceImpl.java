package com.codegym.service.employee.impl;

import com.codegym.entity.employee.*;
import com.codegym.repository.employee.*;
import com.codegym.service.UserDetailsServiceImpl;
import com.codegym.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private EducationDegreeRepository educationDegreeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findByNameContaining(Pageable pageable, String name) {
        return this.employeeRepository.findByNameContaining(pageable, name);
    }

    @Override
    public void deleteEmployee(Integer id) {
        this.employeeRepository.deleteById(id);
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
    public List<Employee> allEmployee() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<String> allNameEmployee() {
        List<Employee> employeeListExist = this.employeeRepository.findAll();
        List<AppUser> appUserListExist = this.userRepository.findAll();
        List<String> employeeListNameExist = new ArrayList<>();
        List<String> appUserListNameExist = new ArrayList<>();

        for (Employee employee : employeeListExist) {
            employeeListNameExist.add(employee.getName());
        }

        for (AppUser appUser : appUserListExist) {
            appUserListNameExist.add(appUser.getUserName());
        }

        employeeListNameExist.removeAll(appUserListNameExist);

        return employeeListNameExist;
    }

    @Override
    public List<Position> allPosition() {
        return this.positionRepository.findAll();
    }

    @Override
    public List<Division> allDivision() {
        return this.divisionRepository.findAll();
    }

    @Override
    public List<EducationDegree> allEducationDegree() {
        return this.educationDegreeRepository.findAll();
    }

    @Override
    public List<AppUser> allUser() {
        return this.userRepository.findAll();
    }

    @Override
    public boolean testRole(AppUser appUser, Long id) {
        Employee employee = new Employee();
        List<Employee> employeeList = this.employeeRepository.findAll();
        for (Employee element : employeeList) {
            if (element.getName().equals(appUser.getUserName())) {
                employee = element;
            }
        }
        return (employee.getPosition().getName().equals("Receptionist")
                || employee.getPosition().getName().equals("Serve")
                || employee.getPosition().getName().equals("Expert")
                || employee.getPosition().getName().equals("Monitoring"))
                && id == 1;

//        if ((employee.getPosition().getName().equals("Receptionist")
//                || employee.getPosition().getName().equals("Serve")
//                || employee.getPosition().getName().equals("Expert")
//                || employee.getPosition().getName().equals("Monitoring"))
//                && id == 1) {
//            return true;
//        }
//        return false;
    }

    @Override
    public String saveUser(AppUser appUser, Long id, String verification) {
        if (verification.equals("ok".concat(appUser.getEncrytedPassword()))) {
            appUser.setEncrytedPassword(this.bCryptPasswordEncoder.encode(appUser.getEncrytedPassword()));
            appUser.setUserId((long) (Math.random() * 1000));
            appUser.setEnabled(true);
            this.userDetailsService.saveNewUser(appUser);
            this.userDetailsService.saveUserRole(appUser, id);
            return "Register Complete !";
        } else {
            return "Your Verification Code is wrong !";
        }
    }
}
