package com.codegym.service;

import com.codegym.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(int id) throws Exception;

    void save(Customer customer);

    void update(Customer customer);

    void remove(int id);
}
