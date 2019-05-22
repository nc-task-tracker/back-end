package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Issue;
import com.example.demo.service.CommentService;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/project/{id}")
    public List<IssueDto> getIssuesByProjectId(@PathVariable(name = "id") String id) {

        return issueService.getIssuesByProjectId(id)
                .stream().map(issue -> modelMapper.map(issue,IssueDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Issue saveIssue(@RequestBody Issue issue) {
        return issueService.saveIssue(issue);
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

    @PostMapping(value = "project/{id}/sort")
    public PageDto<IssueDto> getSortedIssuesByProjectId(@PathVariable(name = "id") String code,
                                              @RequestBody TableSortParametersDTO sortParameters){
        return issueService.getSortedIssuesByProjectId(code,sortParameters);
    }

    @PostMapping(value = "/saveComment")
    public CommentDto saveComment (@RequestBody CommentDto commentDto) {
        return modelMapper.map(commentService.saveComment(modelMapper.map(commentDto, Comment.class)), CommentDto.class);
    }
}
