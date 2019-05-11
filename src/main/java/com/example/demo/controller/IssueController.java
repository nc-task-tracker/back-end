package com.example.demo.controller;

import com.example.demo.dto.IssueDto;
import com.example.demo.model.Issue;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueController(IssueService service) {
        this.issueService = service;
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
    public IssueDto saveIssue(/*@Valid */@RequestBody IssueDto issue) {
        return modelMapper.map(issueService.saveIssue(modelMapper.map(issue, Issue.class)), IssueDto.class);
    }

    @PutMapping
    public IssueDto updateIssue(@RequestBody IssueDto issueForUpdate) {
        Issue issue = modelMapper.map(issueService.getIssueById(issueForUpdate.getId()), Issue.class);
        return modelMapper.map(issueService.updateIssue(issue), IssueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssue(@PathVariable(name = "id") String id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}
