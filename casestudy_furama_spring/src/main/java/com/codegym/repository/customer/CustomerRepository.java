package com.codegym.repository.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.login.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Page<Customer> findAllByFullNameContaining(String name, Pageable pageable);






}
