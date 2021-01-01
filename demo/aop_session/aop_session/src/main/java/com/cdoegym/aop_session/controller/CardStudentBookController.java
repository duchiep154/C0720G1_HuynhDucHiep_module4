package com.cdoegym.aop_session.controller;

import com.cdoegym.aop_session.entity.Book;
import com.cdoegym.aop_session.entity.CardStudent;
import com.cdoegym.aop_session.entity.CardStudentBook;
import com.cdoegym.aop_session.service.BookService;
import com.cdoegym.aop_session.service.CardStudentBookService;
import com.cdoegym.aop_session.service.CardStudentService;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@Aspect
public class CardStudentBookController {

    private static final Logger logger = LoggerFactory.getLogger(CardStudentBookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private CardStudentService cardStudentService;

    @Autowired
    private CardStudentBookService cardStudentBookService;

    @GetMapping("/")
    private ModelAndView getListBook() {
        return new ModelAndView("book/list", "listBook", bookService.getAllBook());
    }

    @GetMapping("/view/{id}")
    private ModelAndView getBook(@PathVariable Long id) {
        Book book = bookService.findById(id);
        Random rand = new Random();
        int random = rand.nextInt(99999 - 10000) + 10000;
        if (book == null) {
            return new ModelAndView("error");
        }
        ModelAndView modelAndView = new ModelAndView("book/view", "cardStudentBook", new CardStudentBook());
        modelAndView.addObject("book", book);
        modelAndView.addObject("random", random);
        modelAndView.addObject("student", cardStudentService.getAll());
        return modelAndView;
    }

    @PostMapping("/rentBook")
    private String rentBook(@ModelAttribute CardStudentBook cardStudentBook) {
        cardStudentBookService.save(cardStudentBook);
        cardStudentBook.getBook().setAmount(cardStudentBook.getBook().getAmount()-1);
        bookService.save(cardStudentBook.getBook());
        logger.info("Success");
        return "redirect:/";
    }

//    @Before("execution(public * com.cdoegym.aop_session.controller.CardStudentBookController.rentBook(..))")
//    public void writeLog() {
//        logger.info("Success hello");
//    }

    @GetMapping("/give_book_back")
    public String viewGiveBookBack() {
        return "book/giveBookBack";
    }

    @PostMapping("/giveBookBack")
    public String giveBookBack(@RequestParam("codeBorrow") Long codeBorrow, Model model) {
        CardStudentBook cardStudentBook = cardStudentBookService.findByCodeBorrow(codeBorrow);
        if(cardStudentBook!= null) {
            Book book =cardStudentBook.getBook();
            book.setAmount(book.getAmount()+1);
            cardStudentBook.setStatus(true);
            cardStudentBookService.save(cardStudentBook);
            bookService.save(book);
        } else {
            model.addAttribute("message","không tìm thấy mã mượn sách");
        }
        return "book/giveBookBack";
    }

}
