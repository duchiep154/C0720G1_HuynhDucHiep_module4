package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.service.CommentService;
import com.codegym.service.CommentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ImageController {
    private CommentService commentService = new CommentServiceImpl();

    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("commentList", commentService.getAllComment());
        return "/display";
    }

    @PostMapping("/comment")
    public String comment(Comment comment) {
        commentService.save(comment);
        return "redirect:/";
    }

    @GetMapping("{id}/{likes}/edit")
    public String edit(@PathVariable("id") int id, @PathVariable("likes") int likes ) {
        Comment comment = commentService.findById(id);
        comment.setCommentLike(likes);
        commentService.edit(comment);
        return "redirect:/";
    }
}
