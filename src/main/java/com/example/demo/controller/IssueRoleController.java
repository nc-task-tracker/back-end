package com.example.demo.controller;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.IssueRoleDto;
import com.example.demo.model.Issue;
import com.example.demo.model.IssueRole;
import com.example.demo.service.IssueRoleService;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/issue_role")
public class IssueRoleController {
    private IssueRoleService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public IssueRoleController(IssueRoleService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public IssueRoleDto getIssueRoleById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getIssueRoleById(id), IssueRoleDto.class);
    }

    @GetMapping(value = "/all")
    public List<IssueRoleDto> getAllIssueRoles() {
        List<IssueRoleDto> issuesRoleDto = new ArrayList<>();
        List<IssueRole> issueRoles = service.getAllIssueRoles();
        for(IssueRole item : issueRoles) {
            issuesRoleDto.add(modelMapper.map(item, IssueRoleDto.class));
        }
        return issuesRoleDto;
    }

    @PostMapping
    public IssueRole saveIssueRole(@RequestBody IssueRole issueRole) {
        return service.saveIssueRole(issueRole);
    }

    @PutMapping
    public IssueRoleDto updateIssueRole(@RequestBody IssueRoleDto issueRoleForUpdate) {
        IssueRole issueRole = modelMapper.map(service.getIssueRoleById(issueRoleForUpdate.getId()), IssueRole.class);
        return modelMapper.map(service.updateIssueRole(issueRole), IssueRoleDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteIssueRole(@PathVariable(name = "id") String id) {
        service.deleteIssueRole(id);
        return ResponseEntity.noContent().build();
    }
}
