package com.codegym.service;

import com.codegym.entity.Customer;
import com.codegym.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAllByProvince(Pageable pageable , Optional<Province> provincial);
    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findById(int id);

    void save(Customer customer);

    void update(Customer customer);

    void remove(int id);

//    void remove(String name);

    Page<Customer> findByNameContaining(Pageable pageable, String name);
}
