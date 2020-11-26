package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import com.codegym.repository.ProductRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
     private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);

    }

    @Override
    public void update(int id, Product product) {
        this.productRepository.update(id,product);

    }

    @Override
    public void remove(int id) {
        this.productRepository.remove(id);

    }
}
