package com.example.demo.controller;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.IssuePriorityDto;
import com.example.demo.model.Issue;
import com.example.demo.model.IssuePriority;
import com.example.demo.service.IssuePriorityService;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/issue_priority")
public class IssuePriorityController {
    private IssuePriorityService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssuePriorityController(IssuePriorityService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public IssuePriorityDto getIssuePriorityById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getIssuePriorityById(id), IssuePriorityDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssuePriorityDto> getAllIssuePriorities() {
        List<IssuePriorityDto> issuePrioritiesDto = new ArrayList<>();
        List<IssuePriority> issuePriorities = service.getAllIssuePriorities();
        for(IssuePriority item : issuePriorities) {
            issuePrioritiesDto.add(modelMapper.map(item, IssuePriorityDto.class));
        }
        return issuePrioritiesDto;
    }

    @PostMapping
    public IssuePriority saveIssuePriority(@RequestBody IssuePriority issuePriority) {
        return service.saveIssuePriority(issuePriority);
    }

    @PutMapping
    public IssuePriorityDto updateIssuePriority(@RequestBody IssuePriorityDto issuePriorityForUpdate) {
        IssuePriority issuePriority = modelMapper.map(service.getIssuePriorityById(issuePriorityForUpdate.getId()), IssuePriority.class);
        return modelMapper.map(service.updateIssuePriority(issuePriority), IssuePriorityDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssuePriority(@PathVariable(name = "id") String id) {
        service.deleteIssuePriority(id);
        return ResponseEntity.noContent().build();
    }
}
