package com.codegym.service;

import com.codegym.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category findById(int id);

    void save(Category category);

    void update(Category category);

    void delete(int id);
}
