package com.example.demo.service.impl;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository repository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment getCommentById(String id) {
        return repository.findCommentById(id);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return (List<Comment>) repository.findAll();
    }

    @Override
    public void deleteComment(String id) {
        repository.deleteById(id);
    }
}
