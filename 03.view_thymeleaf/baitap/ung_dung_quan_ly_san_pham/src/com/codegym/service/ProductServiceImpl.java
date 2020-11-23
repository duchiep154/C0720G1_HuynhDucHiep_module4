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
        products = new HashMap<>();
        products.put(1, new Product(1, "Iphone X", "Điện Thoại", "Apple"));
        products.put(2, new Product(2, "Smart Tivi LG", "Ti vi", "LG"));
        products.put(3, new Product(3, "Samsung A71", "Điện Thoại", "Samsung"));
        products.put(4, new Product(4, "Laptop Asus", "LapTop", "Asus"));
        products.put(5, new Product(5, "Bàn Ủi", "Điện Gia Dụng", "Panasonic"));
        products.put(6, new Product(6, "Nokia 8.1", "Điện Thoại", "Nokia"));
        products.put(7, new Product(7, "Nồi Cơm Điện", "Điện Gia Dụng", "Sunhouse"));
        products.put(8, new Product(8, "Iphone 12", "Điện Thoại", "Apple"));
        products.put(9, new Product(9, "Iphone 11", "Điện Thoại", "Apple"));
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
        products.put(product.getId(), product);
    }

    @Override
    public void update(int id, Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
