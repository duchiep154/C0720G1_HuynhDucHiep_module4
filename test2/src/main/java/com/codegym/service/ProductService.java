package com.codegym.service;

import com.codegym.entity.Product;
import com.codegym.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findByDateImportBetween(Pageable pageable,String dateImport1,String dateImport2);
    Page<Product> findByDateImportContaining(Pageable pageable,String dateImport1);

    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByNameContaining(Pageable pageable, String name);

    Page<Product> findByCostContaining(Pageable pageable, String cost);

//    Page<Product> findByTypeContaining(Pageable pageable, String type);

    Page<Product> findByNameAndCostContaining(Pageable pageable, String name, String cost);

    Page<Product> findAllOfMe(Pageable pageable, String all);

    void deleteProduct(Integer id);

    void save(Product product);

    List<ProductType> allProductType();

    void update(Product product);

    Product findById(Integer id);




    Page<Product> findAllAndSortByID(Pageable pageable);

    Page<Product> findAllAndSortByName(Pageable pageable);

//    Page<Product> findAllAndSortByBirthDay(Pageable pageable);
    //    List<Product> findAllScroll(int start, int limit);
//
}
