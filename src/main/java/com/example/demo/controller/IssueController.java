package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.IssueDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import com.example.demo.service.CommentService;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    private IssueService issueService;
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueController(IssueService issueService, CommentService commentService) {
        this.issueService = issueService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/{id}")
    public IssueDto getIssueById(@PathVariable(name = "id") String id) {
        return modelMapper.map(issueService.getIssueById(id), IssueDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueDto> getAllIssues() {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = issueService.getAllIssues();
        for(Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;
    }

    @PostMapping
    public IssueDto saveIssue(@Valid @RequestBody IssueDto issue) {
        return modelMapper.map(issueService.saveIssue(modelMapper.map(issue, Issue.class)), IssueDto.class);
    }

    @PutMapping(value = "/{id}")
    public IssueDto updateIssue(@PathVariable(name = "id") String id,
                                @RequestBody IssueDto issueDto) {
        issueDto.setId(id);
        return modelMapper.map(issueService.updateIssue(modelMapper.map(issueDto, Issue.class)), IssueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssue(@PathVariable(name = "id") String id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/saveComment")
    public CommentDto saveComment (@PathVariable(name = "id") String id,
                                   @RequestBody CommentDto commentDto) {
        return modelMapper.map(commentService.saveComment(modelMapper.map(commentDto, Comment.class)), CommentDto.class);
    }

    @GetMapping(value = "/allComments")
    public List<CommentDto> getAllComments() {
        List<CommentDto> commentsDto = new ArrayList<>();
        List<Comment> comments = commentService.getAllComments();
        for(Comment item : comments) {
            commentsDto.add(modelMapper.map(item, CommentDto.class));
        }
        return commentsDto;
    }

////    @DeleteMapping(value = "/delete/{id}")
////    public ResponseEntity deleteComment(@PathVariable(name = "id") String id) {
////        commentService.deleteComment(id);
////        return ResponseEntity.noContent().build();
////    }
}
