package com.codegym.service;

import com.codegym.entity.Book;
import com.codegym.entity.CodeBorrow;
import com.codegym.exception.NotAvailableException;
import com.codegym.exception.WrongCodeException;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    void save(Book book);

    Book findById(Integer id);

    void borrow(Book book, Integer usedCode);

    void delete(Integer id);

    CodeBorrow getNextAvailableCode(Book book) throws NotAvailableException;

    void returnBook(Book book, Integer returnCode) throws NotAvailableException, WrongCodeException;

    public boolean checkNoUsedCode(Book book);
}
