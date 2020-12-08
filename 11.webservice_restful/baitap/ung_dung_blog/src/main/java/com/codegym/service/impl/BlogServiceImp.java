package com.codegym.service.impl;

import com.codegym.entity.Blog;
import com.codegym.repository.BlogRepository;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImp implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    @Override
    public List<Blog> findAllBlogAndSort() {
        return this.blogRepository.findAllBlogAndSort();
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return this.blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findByAuthorContaining(Pageable pageable, String author) {
        return this.blogRepository.findByAuthorContaining(pageable, author);
    }

    @Override
    public Blog findById(int id) {
        return this.blogRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        this.blogRepository.save(blog);
    }

    @Override
    public void update(Blog blog) {
        this.blogRepository.save(blog);
    }

    @Override
    public void delete(int id) {
        this.blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> findAllBlogOfCategory(Integer categoryID) {
        List<Blog> blogListResult = new ArrayList<>();
        List<Blog> blogListAll = this.blogRepository.findAll();
        for (Blog blog : blogListAll) {
            if (blog.getCategory().getId() == categoryID) {
                blogListResult.add(blog);
            }
        }
        return blogListResult;
    }
}
