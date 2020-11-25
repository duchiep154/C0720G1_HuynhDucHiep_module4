package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> products;
    static {
        products=new HashMap<>();
        products.put(1,new Product(1,"iphone6","8.000.000","apple","dienthoai","5"));
        products.put(2,new Product(2,"iphone7","9.000.000","apple","dienthoai","5"));
        products.put(3,new Product(3,"iphone8","10.000.000","apple","dienthoai","5"));
        products.put(4,new Product(4,"iphone9","12.000.000","apple","dienthoai","5"));
        products.put(5,new Product(5,"iphone10","14.000.000","apple","dienthoai","5"));
        products.put(6,new Product(6,"iphone11","16.000.000","apple","dienthoai","5"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);

    }

    @Override
    public void update(int id, Product product) {
        products.put(product.getId(),product);


    }

    @Override
    public void remove(int id) {
        products.remove(id);

    }
}
