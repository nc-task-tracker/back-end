package com.example.demo.controller;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;
import com.example.demo.model.projectFilter.ParameterProject;
import com.example.demo.model.projectFilter.ParameterProjectType;
import com.example.demo.model.projectFilter.ProjectFilter;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {
    private ProjectService service;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public ProjectController(ProjectService service, UserService userService, ModelMapper modelMapper) {
        this.service = service;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    public ProjectDto getProjectById(@PathVariable(name = "id") String id) {
        return modelMapper.map(this.service.getProjectById(id), ProjectDto.class);
    }


    @GetMapping(value = "/possibleprojects")
    public List<ProjectDto>  getPossibleProjectsByUser(@RequestParam(name = "username") String username){
        List<ProjectDto> projectsDto = new ArrayList<>();
        List<Project> possibleProjects = userService.getPossibleProjects(username);
        for (Project item : possibleProjects) {
            projectsDto.add(modelMapper.map(item, ProjectDto.class));
        }
        return projectsDto;
    }

    @GetMapping
    public List<ProjectDto> searchProjects(@RequestParam(name = "name", required = false) String name,
                                           @RequestParam(name = "code", required = false) String code) {
        List<ProjectDto> result = new ArrayList<>();

        ProjectFilter projectFilter = new ProjectFilter();
        if (name != null) {
            projectFilter.getParameters().add(new ParameterProject(name, ParameterProjectType.PROJECT_NAME));
        }
        if (code != null) {
            projectFilter.getParameters().add(new ParameterProject(code, ParameterProjectType.PROJECT_CODE));
        }
        this.service.searchProject(projectFilter).forEach(
                project -> result.add(modelMapper.map(project, ProjectDto.class)));
        return result;
    }

    @GetMapping(value = "/all")
    public List<ProjectDto> getAllProjects() {
        return service.getAllProjects().stream()
                .map(value -> modelMapper.map(value,ProjectDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/all/sorted")
    public PageDto<ProjectDto> getAllSortedProjects(@RequestBody TableSortParametersDTO parameters){
        PageDto<ProjectDto> result =  service.getAllSortedProjects(parameters);
        return result;
    }

    @PostMapping(value = "/create")
    public ProjectDto createProject(@RequestBody @Valid ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);
        return modelMapper.map(service.createProject(project), ProjectDto.class);
    }

    @PutMapping
    public ProjectDto updateProject(@RequestBody @Valid ProjectDto projectForUpdate) {
        Project project = modelMapper.map(service.getProjectById(projectForUpdate.getId()), Project.class);
        return modelMapper.map(service.updateProject(project), ProjectDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable(name = "id") String id) {
        service.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
