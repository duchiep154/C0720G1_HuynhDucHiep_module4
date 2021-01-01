package com.codegym.repository.service;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
    Page<Service> findByNameContaining (Pageable pageable, String name);
}
