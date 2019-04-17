package com.example.demo.controller;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import com.example.demo.service.mappers.ProjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {
    private ProjectService service;

    private final ModelMapper modelMapper;

    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ProjectService service, ModelMapper modelMapper, ProjectMapper projectMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.projectMapper = projectMapper;
    }

    @GetMapping(value = "/{id}")
    public ProjectDto getProjectById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getProjectById(id), ProjectDto.class);
    }

    @GetMapping(value = "/all")
    public List<ProjectDto> getAllProjects() {
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> projects = service.getAllProjects();
        for (Project item : projects) {
            projectsDto.add(modelMapper.map(item, ProjectDto.class));
        }
        return projectsDto;
    }

    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        return projectMapper.convertToDto(service.createProject(projectMapper.convertToEntity(projectDto)));
    }

    @PutMapping
    public ProjectDto updateProject(@RequestBody ProjectDto projectForUpdate) {
        Project project = modelMapper.map(service.getProjectById(projectForUpdate.getId()), Project.class);
        return modelMapper.map(service.updateProject(project), ProjectDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable(name = "id") String id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
