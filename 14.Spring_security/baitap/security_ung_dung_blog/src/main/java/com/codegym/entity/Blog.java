package com.codegym.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity (name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String author;
    private String contentBlog;
    private String date;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    @JsonBackReference
    @JsonManagedReference
    private Category category;

    public Blog() {
    }

    public Blog(int id, String author, String contentBlog, String date) {
        this.id = id;
        this.author = author;
        this.contentBlog = contentBlog;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentBlog() {
        return contentBlog;
    }

    public void setContentBlog(String contentBlog) {
        this.contentBlog = contentBlog;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
