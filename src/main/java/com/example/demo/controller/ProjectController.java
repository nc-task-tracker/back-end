package com.example.demo.controller;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {
    private ProjectService service;
    private ModelMapper modelMapper;

    @Autowired
    public ProjectController(ProjectService service,ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public ProjectDto getProjectById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getProjectById(id), ProjectDto.class);
    }

    @GetMapping(value = "/all")
    public List<ProjectDto> getAllProjects() {
        return service.getAllProjects().stream()
                .map(value -> modelMapper.map(value,ProjectDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/all/sorted")
    public PageDto<ProjectDto> getAllSortedProjects(@RequestBody TableSortParametersDTO parameters){
        System.out.println(parameters.toString());
        return service.getAllSortedProjects(parameters);
    }

    @PostMapping
    public Project saveProject(@RequestBody ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto,Project.class);
        return service.saveProject(project);
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

    @PostMapping(value = "/add/assigner/{id}")
    public ResponseEntity addAssigner(@RequestBody UserDto userDto,
                                      @PathVariable(name = "id") String id){
        //System.out.println("123" + projectId);
        System.out.println(userDto.toString());
        //service.addAssigner(projectId,userDto.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{projectId}/delete/assigner/{userId}")
    public ResponseEntity deleteAssigner(@PathVariable(name = "projectId") String projectId,
                                         @PathVariable(name = "userId") String id){
        service.deleteAssigner(projectId,id);
        return ResponseEntity.noContent().build();
    }
}
