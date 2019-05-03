package com.example.demo.controller;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.TableSortParametersDTO;
import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    private IssueService service;
    private ModelMapper modelMapper;

    @Autowired
    public IssueController(IssueService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public IssueDto getIssueById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getIssueById(id), IssueDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueDto> getAllIssues() {
        List<IssueDto> issuesDto = new ArrayList<>();
        List<Issue> issues = service.getAllIssues();
        for (Issue item : issues) {
            issuesDto.add(modelMapper.map(item, IssueDto.class));
        }
        return issuesDto;
    }

    @GetMapping(value = "/project/{id}")
    public List<IssueDto> getIssuesByProjectId(@PathVariable(name = "id") String id) {

        List<IssueDto> issuesDto = service.getIssuesByProjectId(id)
                .stream().map(issue ->
                    modelMapper.map(issue,IssueDto.class)
                ).collect(Collectors.toList());

        return issuesDto;
    }

    @PostMapping
    public Issue saveIssue(@RequestBody IssueDto issue) {
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

    @PostMapping(value = "project/{id}/sort")
    public List<IssueDto> getSortedIssuesByProjectId(@PathVariable(name = "id") String id,
                                                     @RequestBody TableSortParametersDTO sortParameters){
        return service.getSortedIssuesByProjectId(id,sortParameters)
                .stream().map(issue -> modelMapper.map(issue,IssueDto.class))
                .collect(Collectors.toList());
    }
}
