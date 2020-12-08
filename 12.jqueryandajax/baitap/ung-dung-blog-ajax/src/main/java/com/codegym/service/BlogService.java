package com.codegym.service;

import com.codegym.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    List<Blog> findAllLoad(int start, int limit);

    List<Blog> findAllBlogAndSort();

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findByAuthorContaining(Pageable pageable, String author);

    Blog findById(int id);

    void save(Blog blog);

    void update(Blog blog);

    void delete(int id);

    List<Blog> findAllBlogOfCategory(Integer categoryID);
}
