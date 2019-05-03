package com.example.demo.controller;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.UpdateIssueDto;
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

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    private IssueService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueController(IssueService issueService, CommentService commentServeice) {
        this.service = issueService;
    }

    @GetMapping(value = "/{id}")
    public IssueDto getIssueById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getIssueById(id), IssueDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueDto> getAllIssues() {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = service.getAllIssues();
        for(Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;
    }

    @PostMapping
    public Issue saveIssue(@RequestBody Issue issue) {
        return service.saveIssue(issue);
    }

    @PutMapping("{id}/acton")
    public IssueDto updateIssue(@PathVariable(name = "id") String id,
                                @Valid @RequestBody IssueDto issueDto) {
        issueDto.setId(id);
        return modelMapper.map(service.updateIssue(modelMapper.map(issueDto, Issue.class)), IssueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssue(@PathVariable(name = "id") String id) {
        service.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}
