package com.codegym.service;

import com.codegym.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment();

    Comment findById(int id);

    void save(Comment comment);

    void edit(Comment comment);
}
