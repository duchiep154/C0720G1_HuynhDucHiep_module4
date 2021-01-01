package com.codegym.repository.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.TypeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeCustomerRepository extends JpaRepository<TypeCustomer, Integer> {

}
