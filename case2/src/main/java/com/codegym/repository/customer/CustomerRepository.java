package com.codegym.repository.customer;

import com.codegym.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Page<Customer> findByNameContaining(Pageable pageable, String name);

    @Query(value = "select * from customer group by id order by id", nativeQuery = true)
    Page<Customer> findAllAndSortByID(Pageable pageable);

    @Query(value = "select * from customer group by id order by `name`", nativeQuery = true)
    Page<Customer> findAllAndSortByName(Pageable pageable);

    @Query(value = "select * from customer group by id order by birth_day desc", nativeQuery = true)
    Page<Customer> findAllAndSortByBirthDay(Pageable pageable);
}