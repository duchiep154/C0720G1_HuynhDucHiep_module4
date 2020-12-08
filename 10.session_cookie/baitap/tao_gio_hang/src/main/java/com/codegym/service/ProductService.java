package com.codegym.service;

import com.codegym.entity.Product;

public interface ProductService {
    Iterable<Product> findAll();

    Product findById(long id);

    void save(Product product);

    void delete(long id);
}
