package com.codegym.service.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllAndSortByID(Pageable pageable);

    Page<Customer> findAllAndSortByName(Pageable pageable);

    Page<Customer> findAllAndSortByBirthDay(Pageable pageable);

    Page<Customer> findByNameContaining(Pageable pageable, String name);

    void deleteCustomer (String id);

    void save(Customer customer);

    List<CustomerType> allCustomerType();

    void update(Customer customer);
}
