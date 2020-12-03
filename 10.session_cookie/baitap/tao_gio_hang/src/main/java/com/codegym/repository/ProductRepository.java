package com.codegym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codegym.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
