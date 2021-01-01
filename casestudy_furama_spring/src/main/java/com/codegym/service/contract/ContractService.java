package com.codegym.service.contract;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.customer.Customer;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Page<Contract> findAll(Pageable pageable);

    Contract findById(Integer id);

    void save(Contract contract);

    void update(Contract contract);

    void remove(Integer id);

    List<Service> findAllService();

    List<Customer> findAllCustomer();

    List<Employee> findAllEmployee();
}
