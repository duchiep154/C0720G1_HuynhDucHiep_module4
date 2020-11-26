package com.codegym.service;

import com.codegym.model.Comment;
import com.codegym.repository.CommentRepository;
import com.codegym.repository.CommentRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository = new CommentRepositoryImpl();
    @Override
    public List<Comment> getAllComment() {
        return commentRepository.getAllComment();
    }

    @Override
    public Comment findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void edit(Comment comment) {
        commentRepository.edit(comment);
    }
}
