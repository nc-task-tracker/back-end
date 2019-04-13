package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/comment")
public class CommentController {
    private CommentService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public CommentDto getCommentById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getCommentById(id), CommentDto.class);
    }

    @GetMapping(value = "/all")
    public List<CommentDto> getAllComments() {
        List<CommentDto> commentsDto = new ArrayList<>();
        List<Comment> comments = service.getAllComments();
        for(Comment item : comments) {
            commentsDto.add(modelMapper.map(item, CommentDto.class));
        }
        return commentsDto;
    }

    @PostMapping
    public Comment saveComment(@RequestBody Comment comment) {
        return service.saveComment(comment);
    }

    @PutMapping
    public CommentDto updateComment(@RequestBody CommentDto commentForUpdate) {
        Comment comment = modelMapper.map(service.getCommentById(commentForUpdate.getId()), Comment.class);
        return modelMapper.map(service.updateComment(comment), CommentDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable(name = "id") String id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
