package com.codegym.controller;

import com.codegym.entity.Blog;
import com.codegym.entity.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class RestCategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    // Xem danh sách các category
    @GetMapping("/restCategory/")
    public ResponseEntity<List<Category>> listAllCategory() {
        List<Category> category = categoryService.findAll();
        if (category.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
    }

    // Xem danh sách các bài viết
    @GetMapping("/restBlog/")
    public ResponseEntity<List<Blog>> listAllBlog() {
        List<Blog> blogs = blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
    }

    // Xem danh sách các bài viết của một category
    @GetMapping("/blogOfCategory/{id}")
    public ResponseEntity<List<Blog>> blogOfCategory(@PathVariable int id) {
        List<Blog> blogList = blogService.findAll();
        List<Blog> blogResult = new ArrayList<>();
        for (Blog blog : blogList) {
            if (blog.getCategory().getId() == id) {
                blogResult.add(blog);
            }
        }
        return new ResponseEntity<List<Blog>>(blogResult, HttpStatus.OK);
    }

    // Xem chi tiết 1 bài viết
    @GetMapping("/blogOfBlog/{id}")
    public ResponseEntity<Blog> blogOfBlog(@PathVariable int id) {
        Blog blog = this.blogService.findById(id);
        return new ResponseEntity<Blog>(blog, HttpStatus.OK);
    }


    @GetMapping("/restBlog/{start}/{limit}")
    public ResponseEntity<List<Blog>> loadBlog(@PathVariable int start, @PathVariable int limit) {
        List<Blog> blogList = this.blogService.findAllLoad(start, limit);
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
}
