package com.example.demo.service.impl;

import com.example.demo.model.Comment;
import com.example.demo.model.Profile;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository repository;
    private ProfileRepository profileRepository;

    @Autowired
    public CommentServiceImpl (CommentRepository repository,
                               ProfileRepository profileRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Comment saveComment(Comment comment, String profileId) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Profile dbProfile = profileRepository.findProfileById (profileId);
        comment.setProfile (dbProfile);
        try {
            comment.setTime (formatter.parse(formatter.format (date)));
        } catch (ParseException e) {
            e.printStackTrace ();
        }
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
