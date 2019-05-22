package com.example.demo.controller;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.service.ProjectService;
import org.modelmapper.ModelMapper;
import com.example.demo.service.mappers.ProjectMapper;
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
    private ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ProjectService service, ModelMapper modelMapper, ProjectMapper projectMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.projectMapper = projectMapper;
    }

    @GetMapping(value = "/{id}")
    public ProjectDto getProjectById(@PathVariable(name = "id") String id) {
        return projectMapper.convertToDto(this.service.getProjectById(id));
    }

//    @GetMapping(value = "/{code}")
//    public ProjectDto getProjectByCode(@PathVariable(name = "code") String code) {
//        return projectMapper.convertToDto(this.service.getProjectByCode(code));
//    }

    @GetMapping
    public List<ProjectDto> searchProjects(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "code") String code) {
        List<ProjectDto> result = new ArrayList<>();

        /*ProjectFilter projectFilter = new ProjectFilter();
        projectFilter.getParameters().add(new ParameterProject(name, ParameterProjectType.PROJECT_NAME));
        projectFilter.getParameters().add(new ParameterProject(code, ParameterProjectType.PROJECT_CODE));

        this.service.searchProject(projectFilter).forEach(
                project -> result.add(projectMapper.convertToDto(project)));*/
        if(!name.equals("null")){
            result.add(projectMapper.convertToDto(this.service.getProjectByName(name)));
        }
        if(!code.equals("null")){
            result.add(projectMapper.convertToDto(this.service.getProjectByCode(code)));
        }
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
        return service.getAllSortedProjects(parameters);
    }

    @PostMapping
    public ProjectDto createProject(@RequestBody @Valid ProjectDto projectDto) {
        return projectMapper.convertToDto(service.createProject(projectMapper.convertToEntity(projectDto)));
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

//    @PostMapping(value = "/add/assigner/{id}")
//    public ResponseEntity addAssigner(@RequestBody UserDto userDto,
//                                      @PathVariable(name = "id") String id){
//        service.addAssigner(id,userDto.getId());
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping(value = "/{projectId}/delete/assigner/{userId}")
//    public ResponseEntity deleteAssigner(@PathVariable(name = "projectId") String projectId,
//                                         @PathVariable(name = "userId") String id){
//        service.deleteAssigner(projectId,id);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/{id}/members")
    public List<ProjectMemberDto> getProjectMembers(@PathVariable(name = "id") String id){
        return service.getProjectMembers(id);
    }
}
