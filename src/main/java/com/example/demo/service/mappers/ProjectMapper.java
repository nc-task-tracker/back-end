package com.example.demo.service.mappers;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Project;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ProjectMapper {
    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public ProjectMapper(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public ProjectDto convertToDto(Project project) {
        if(project == null)
            return null;
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        projectDto.setOwnerId(project.getOwner().getId());
        return projectDto;
    }

    public Project convertToEntity(ProjectDto projectDto){
        if(projectDto == null)
            return null;
        Project project = modelMapper.map(projectDto, Project.class);
        project.setOwner(userService.getUserById(projectDto.getOwnerId()));
        return project;
    }
}
