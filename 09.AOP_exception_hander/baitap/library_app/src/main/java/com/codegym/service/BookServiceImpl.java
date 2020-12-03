package com.codegym.service;

import com.codegym.entity.Book;
import com.codegym.entity.CodeBorrow;
import com.codegym.entity.Status;
import com.codegym.exception.NotAvailableException;
import com.codegym.exception.WrongCodeException;
import com.codegym.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CodeBorrowService codeBorrowService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
        Status available = new Status(1);
        for (int i = 0; i < book.getQuantity(); i++) {
            int n = 10000 + new Random().nextInt(90000);
            CodeBorrow codeBorrow = new CodeBorrow(n, bookRepository.findById(book.getId()).orElse(null), available);
            book.generateCode(codeBorrow);
            codeBorrowService.save(codeBorrow);
        }
        bookRepository.save(book);
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void borrow(Book book, Integer usedCode) {
        List<CodeBorrow> codeBorrowList = codeBorrowService.findAllCodeByBookId(book.getId());
        for (CodeBorrow codeBorrow : codeBorrowList) {
            if (codeBorrow.getCode().equals(usedCode)) {
                codeBorrow.setStatus(new Status(2, "used"));
                break;
            }
        }
        book.borrow();
        bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public CodeBorrow getNextAvailableCode(Book book) throws NotAvailableException {
        List<CodeBorrow> codeBorrowList = codeBorrowService.findAvailableCodeByBookId(book.getId());
        if (codeBorrowList.size() == 0) {
            throw new NotAvailableException();
        }
        return codeBorrowList.get(0);
    }

    @Override
    public void returnBook(Book book, Integer returnCode) throws NotAvailableException, WrongCodeException {
        List<CodeBorrow> codeBorrowList = codeBorrowService.findUsedCodeByBookId(book.getId());
        if (codeBorrowList.size() == 0) {
            throw new NotAvailableException();
        }
        boolean isCorrectCode = false;
        for (CodeBorrow codeBorrow : codeBorrowList) {
            if (codeBorrow.getCode().equals(returnCode)) {
                codeBorrow.setStatus(new Status(1, "available"));
                codeBorrowService.save(codeBorrow);
                book.returnBook();
                bookRepository.save(book);
                isCorrectCode = true;
                break;
            }
        }
        if (!isCorrectCode) {
            throw new WrongCodeException();
        }
    }

    @Override
    public boolean checkNoUsedCode(Book book) {
        List<CodeBorrow> availableCodeBorrowList = codeBorrowService.findAvailableCodeByBookId(book.getId());
        List<CodeBorrow> allCodeBorrowList = codeBorrowService.findAllCodeByBookId(book.getId());
        return availableCodeBorrowList.size() == allCodeBorrowList.size();
    }
}
