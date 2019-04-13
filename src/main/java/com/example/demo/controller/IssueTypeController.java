package com.example.demo.controller;

import com.example.demo.dto.IssueTypeDto;
import com.example.demo.model.IssueType;
import com.example.demo.service.IssueTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/issue_type")
public class IssueTypeController {
    private IssueTypeService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueTypeController(IssueTypeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public IssueTypeDto getIssueTypeById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getIssueTypeById(id), IssueTypeDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueTypeDto> getAllIssueTypes() {
        List<IssueTypeDto> issuesTypeDto = new ArrayList<>();
        List<IssueType> issueTypes = service.getAllIssueTypes();
        for(IssueType item : issueTypes) {
            issuesTypeDto.add(modelMapper.map(item, IssueTypeDto.class));
        }
        return issuesTypeDto;
    }

    @PostMapping
    public IssueType saveIssueType(@RequestBody IssueType issueType) {
        return service.saveIssueType(issueType);
    }

    @PutMapping
    public IssueTypeDto updateIssueType(@RequestBody IssueTypeDto issueTypeForUpdate) {
        IssueType issueType = modelMapper.map(service.getIssueTypeById(issueTypeForUpdate.getId()), IssueType.class);
        return modelMapper.map(service.updateIssueType(issueType), IssueTypeDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssueType(@PathVariable(name = "id") String id) {
        service.deleteIssueType(id);
        return ResponseEntity.noContent().build();
    }
}
