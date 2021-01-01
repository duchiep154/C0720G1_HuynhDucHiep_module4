package com.codegym.service.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.TypeCustomer;
import com.codegym.entity.login.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Customer findById(String id);

    void save(Customer customer);

    void update(Customer customer);

    void remove(String id);

    List<TypeCustomer> findAllTypeCustomer();
    List<AppUser> findAllAppUser();

    Page<Customer> findByNameContaining (Pageable pageable, String name);
}
