package com.example.demo.controller;

import com.example.demo.dto.IssueStatusDto;
import com.example.demo.model.IssueStatus;
import com.example.demo.service.IssueStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/user_status")
public class IssueStatusController {
    private IssueStatusService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueStatusController(IssueStatusService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public IssueStatusDto getIssueStatusById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getIssueStatusById(id), IssueStatusDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueStatusDto> getAllIssueStatuses() {
        List<IssueStatusDto> issuesStatusDto = new ArrayList<>();
        List<IssueStatus> issueStatuses = service.getAllIssueStatuses();
        for(IssueStatus item : issueStatuses) {
            issuesStatusDto.add(modelMapper.map(item, IssueStatusDto.class));
        }
        return issuesStatusDto;
    }

    @PostMapping
    public IssueStatus saveIssueStatus(@RequestBody IssueStatus issueStatus) {
        return service.saveIssueStatus(issueStatus);
    }

    @PutMapping
    public IssueStatusDto updateIssueStatus(@RequestBody IssueStatusDto issueStatusForUpdate) {
        IssueStatus issueStatus = modelMapper.map(service.getIssueStatusById(issueStatusForUpdate.getId()), IssueStatus.class);
        return modelMapper.map(service.updateIssueStatus(issueStatus), IssueStatusDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteStatusIssue(@PathVariable(name = "id") String id) {
        service.deleteIssueStatus(id);
        return ResponseEntity.noContent().build();
    }
}
