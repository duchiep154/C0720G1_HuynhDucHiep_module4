package com.cdoegym.aop_session.service;

import com.cdoegym.aop_session.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book findById(Long id);

    void save(Book book);
}
