package com.example.demo.controller;

import com.example.demo.dto.FilterDto;
import com.example.demo.dto.IssueDto;
import com.example.demo.model.Filter;
import com.example.demo.model.Issue;
import com.example.demo.model.ModelForSearch;
import com.example.demo.model.UserProfile;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    private IssueService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueController(IssueService service) {
        this.service = service;
    }

//    @GetMapping(value = "/{id}")
//    public IssueDto getIssueById(@PathVariable(name = "id") String id) {
//        return modelMapper.map(service.getIssueById(id), IssueDto.class);
//    }

    @GetMapping(value = "/{id}")
    public Issue getIssueById(@PathVariable(name = "id") String id) {
        return service.getIssueById(id);
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

    @PutMapping
    public IssueDto updateIssue(@RequestBody IssueDto issueForUpdate) {
        Issue issue = modelMapper.map(service.getIssueById(issueForUpdate.getId()), Issue.class);
        return modelMapper.map(service.updateIssue(issue), IssueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssue(@PathVariable(name = "id") String id) {
        service.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search/")
    public List<IssueDto> findAllIssuesByPredicates(@RequestBody FilterDto filterDto) {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = service.searchIssue(modelMapper.map(filterDto, Filter.class));
        for(Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;
    }

//    @GetMapping(value="/assignee")
//    public List<UserProfile> getAssignee(@RequestParam(required = false) String substring) {
//        return service.getAssigneeList(substring);
//    }

    @GetMapping(value="/assigneeName")
    public List<ModelForSearch> getAssignee(@RequestParam(required = false) String substring) {
        return service.searchAssignee(substring);
    }

    @GetMapping(value="/reporterName")
    public List<ModelForSearch> getReporter(@RequestParam(required = false) String substring) {
        return service.searchReporter(substring);
    }
}
