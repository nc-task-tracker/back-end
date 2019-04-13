package com.example.demo.controller;

import com.example.demo.dto.ProjectStatusDto;
import com.example.demo.model.ProjectStatus;
import com.example.demo.service.ProjectStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/project_status")
public class ProjectStatusController {
    private ProjectStatusService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProjectStatusController(ProjectStatusService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ProjectStatusDto getProjectStatus(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getProjectStatusById(id), ProjectStatusDto.class);
    }

    @GetMapping(value = "/all")
    public List<ProjectStatusDto> getAllProjectStatus() {
        List<ProjectStatusDto> projectStatusesDto = new ArrayList<>();
        List<ProjectStatus> projectStatuses = service.getAllProjectStatuses();
        for(ProjectStatus item : projectStatuses) {
            projectStatusesDto.add(modelMapper.map(item, ProjectStatusDto.class));
        }
        return projectStatusesDto;
    }

    @PostMapping
    public ProjectStatus saveProjectStatus(@RequestBody ProjectStatus projectStatus) {
        return service.saveProjectStatus(projectStatus);
    }

    @PutMapping
    public ProjectStatusDto updateProjectStatus(@RequestBody ProjectStatusDto projectStatusForUpdate) {
        ProjectStatus projectStatus = modelMapper.map(service.getProjectStatusById(projectStatusForUpdate.getId()), ProjectStatus.class);
        return modelMapper.map(service.updateProjectStatus(projectStatus), ProjectStatusDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") String id) {
        service.deleteProjectStatus(id);
        return ResponseEntity.noContent().build();
    }
}
