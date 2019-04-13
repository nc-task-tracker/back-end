package com.example.demo.controller;

import com.example.demo.dto.ChildIssueDto;
import com.example.demo.model.ChildIssue;
import com.example.demo.service.ChildIssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/child_issue")
public class ChildIssueController {
    private ChildIssueService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ChildIssueController(ChildIssueService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ChildIssueDto getChildIssueById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getChildIssueById(id), ChildIssueDto.class);
    }

    @GetMapping(value = "/all")
    public List<ChildIssueDto> getAllChildIssues() {
        List<ChildIssueDto> childIssuesDto = new ArrayList<>();
        List<ChildIssue> childIssues = service.getAllChildIssues();
        for(ChildIssue item : childIssues) {
            childIssuesDto.add(modelMapper.map(item, ChildIssueDto.class));
        }
        return childIssuesDto;
    }

    @PostMapping
    public ChildIssue saveChildIssue(@RequestBody ChildIssue childIssue) {
        return service.saveChildIssue(childIssue);
    }

    @PutMapping
    public ChildIssueDto updateChildIssue(@RequestBody ChildIssueDto childIssueForUpdate) {
        ChildIssue childIssue = modelMapper.map(service.getChildIssueById(childIssueForUpdate.getId()), ChildIssue.class);
        return modelMapper.map(service.updateChildIssue(childIssue), ChildIssueDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteChildIssue(@PathVariable(name = "id") String id) {
        service.deleteChildIssue(id);
        return ResponseEntity.noContent().build();
    }
}
