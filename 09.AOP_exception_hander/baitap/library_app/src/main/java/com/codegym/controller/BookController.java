package com.codegym.controller;

import com.codegym.entity.Book;
import com.codegym.exception.NotAvailableException;
import com.codegym.exception.NotBorrowException;
import com.codegym.exception.WrongCodeException;
import com.codegym.service.BookService;
import com.codegym.service.CodeBorrowService;
import com.codegym.service.StatusService;
import com.codegym.validation.ReturnCodeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    CodeBorrowService codeBorrowService;

    @Autowired
    StatusService statusService;

    @GetMapping()
    public String home() {
        return "layout";
    }

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("bookList", bookService.findAll());
        return "view";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        return "create";
    }

    @PostMapping("/create")
    public String createBook(Model model, @Validated @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute(book);
            return "create";
        }
        bookService.save(book);
        return "redirect:/view";
    }

    @GetMapping("/borrow")
    public String borrow(Model model, @RequestParam Integer id) throws NotAvailableException {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("availableCode", bookService.getNextAvailableCode(book));
        return "borrow";
    }

    @PostMapping("/borrow")
    public String borrowBook(@ModelAttribute Book book, @RequestParam Integer usedCode) {
        bookService.borrow(book, usedCode);
        return "redirect:/view";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        bookService.delete(id);
        return "redirect:/view";
    }

    @GetMapping("/return")
    public String returnPage(Model model, @RequestParam Integer id, @ModelAttribute ReturnCodeValidate returnCodeValidate) throws NotBorrowException {
        Book book = bookService.findById(id);
        if (bookService.checkNoUsedCode(book)) {
            throw new NotBorrowException();
        }
        model.addAttribute("returnCodeValidate", returnCodeValidate);
        model.addAttribute("book", book);
        return "return";
    }

    @PostMapping("/return")
    public String returnBook(Model model, @ModelAttribute Book book, @Validated @ModelAttribute ReturnCodeValidate returnCodeValidate, BindingResult result)
            throws NotAvailableException, WrongCodeException {
        if (result.hasFieldErrors()) {
            model.addAttribute("returnCodeWrapper", returnCodeValidate);
            model.addAttribute("book", book);
            return "return";
        }
        bookService.returnBook(book, Integer.parseInt(returnCodeValidate.getReturnCode()));
        return "redirect:/view";
    }

    @GetMapping("/create_code_status")
    public String createCodeStatus() {
        statusService.createStatus();
        return "layout";
    }

    @ExceptionHandler(NotAvailableException.class)
    public String notAvailable() {
        return "error_not_available";
    }

    @ExceptionHandler(WrongCodeException.class)
    public String wrongCode() {
        return "error_wrong_code";
    }

    @ExceptionHandler(NotBorrowException.class)
    public String notBorrow() {
        return "error_not_borrow";
    }

}
