package com.codegym.controller;

import com.codegym.entity.CodeBorrow;
import com.codegym.service.BookService;
import com.codegym.service.CodeBorrowService;
import com.codegym.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CodeBorrowController {
    @Autowired
    BookService bookService;

    @Autowired
    CodeBorrowService codeBorrowService;

    @Autowired
    StatusService statusService;

    @GetMapping("/viewborrow")
    public String view(Model model) {
        List<CodeBorrow> codeBorrowList = codeBorrowService.findAll();
        model.addAttribute("codeBorrowList", codeBorrowList);
        return "view_code_borrow";
    }

}
