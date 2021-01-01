package com.codegym.service;

import com.codegym.entity.Product;
import com.codegym.entity.ProductType;
import com.codegym.repository.ProductRepository;
import com.codegym.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public Page<Product> findByDateImportBetween(Pageable pageable, String dateImport1, String dateImport2) {
        return productRepository.findByDateImportBetween(pageable,dateImport1,dateImport2);
    }

    @Override
    public Page<Product> findByDateImportContaining(Pageable pageable, String dateImport1) {
        return productRepository.findByDateImportContaining(pageable, dateImport1);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllAndSortByID(Pageable pageable) {
        return this.productRepository.findAllAndSortByID(pageable);
    }

    @Override
    public Page<Product> findAllAndSortByName(Pageable pageable) {
        return this.productRepository.findAllAndSortByName(pageable);
    }
//
//    @Override
//    public Page<Product> findAllAndSortByBirthDay(Pageable pageable) {
//        return this.productRepository.findAllAndSortByBirthDay(pageable);
//    }

    @Override
    public Page<Product> findByNameContaining(Pageable pageable, String name) {
        return this.productRepository.findByNameContaining(pageable, name);
    }

    @Override
    public Page<Product> findByCostContaining(Pageable pageable, String cost) {
        return this.productRepository.findByCostContaining(pageable, cost);
    }

//    @Override
//    public Page<Product> findByTypeContaining(Pageable pageable, String type) {
//        return this.productRepository.f;
//    }

    @Override
    public Page<Product> findByNameAndCostContaining(Pageable pageable, String name, String cost) {
        return this.productRepository.findByNameAndCostContainingOfMe(pageable, name, cost);
    }

    @Override
    public Page<Product> findAllOfMe(Pageable pageable, String all) {
        return this.productRepository.findAllOfMe(pageable,all);
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public List<ProductType> allProductType() {
        return this.productTypeRepository.findAll();
    }

    @Override
    public void update(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }
}
