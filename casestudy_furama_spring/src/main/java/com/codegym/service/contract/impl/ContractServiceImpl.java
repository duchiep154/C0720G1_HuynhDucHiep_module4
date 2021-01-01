package com.codegym.service.contract.impl;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.customer.Customer;
import com.codegym.entity.employee.Employee;
import com.codegym.repository.contract.ContractRepository;
import com.codegym.repository.customer.CustomerRepository;
import com.codegym.repository.employee.EmployeeRepository;
import com.codegym.repository.service.ServiceRepository;
import com.codegym.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Contract> findAll() {
        return this.contractRepository.findAll();
    }

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return this.contractRepository.findAll(pageable);
    }

    @Override
    public Contract findById(Integer id) {
        return this.contractRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Contract contract) {
        this.contractRepository.save(contract);
    }

    @Override
    public void update(Contract contract) {
        this.contractRepository.save(contract);
    }

    @Override
    public void remove(Integer id) {
        this.contractRepository.deleteById(id);
    }

    @Override
    public List<com.codegym.entity.service.Service> findAllService() {
        return this.serviceRepository.findAll();
    }

    @Override
    public List<Customer> findAllCustomer() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<Employee> findAllEmployee() {
        return this.employeeRepository.findAll();
    }
}
