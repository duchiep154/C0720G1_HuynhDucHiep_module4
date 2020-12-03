package com.codegym.repository;

import com.codegym.entity.Customer;
import com.codegym.entity.Provincial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    List<Customer> findAllByNameContaining(String name);
//    Page<Customer>findAllByProvince(Pageable pageable,String provincial);


    Page<Customer> findAllByNameContaining(Pageable pageable, String name);

    //    @Query(value = "select * from student where `name` like %?1%", nativeQuery = true)
    @Query(value = "select * from customer where `name` like concat('%', ?1, '%')", nativeQuery = true)

    List<Customer> searchStudentByName(String name);

    Page<Customer> findAllByProvincial(Pageable pageable, Optional<Provincial> provincial);
}
