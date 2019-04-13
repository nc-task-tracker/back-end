package com.example.demo.controller;

import com.example.demo.dto.ProjectTypeDto;
import com.example.demo.model.ProjectType;
import com.example.demo.service.ProjectTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/project_type")
public class ProjectTypeController {
    private ProjectTypeService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProjectTypeController(ProjectTypeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ProjectTypeDto getProjectTypeById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getProjectTypeById(id), ProjectTypeDto.class);
    }

    @GetMapping(value = "/all")
    public List<ProjectTypeDto> getAllProjectType() {
        List<ProjectTypeDto> projectTypesDto = new ArrayList<>();
        List<ProjectType> projectTypes = service.getAllProjectTypes();
        for(ProjectType item : projectTypes) {
            projectTypesDto.add(modelMapper.map(item, ProjectTypeDto.class));
        }
        return projectTypesDto;
    }

    @PostMapping
    public ProjectType saveProjectType(@RequestBody ProjectType projectType) {
        return service.saveProjectType(projectType);
    }

    @PutMapping
    public ProjectTypeDto updateProjectType(@RequestBody ProjectTypeDto projectTypeForUpdate) {
        ProjectType projectType = modelMapper.map(service.getProjectTypeById(projectTypeForUpdate.getId()), ProjectType.class);
        return modelMapper.map(service.updateProjectType(projectType), ProjectTypeDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProjectType(@PathVariable(name = "id") String id) {
        service.deleteProjectType(id);
        return ResponseEntity.noContent().build();
    }
}
