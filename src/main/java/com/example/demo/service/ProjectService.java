package com.example.demo.service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    Project getProjectById(String id);
    Project updateProject(Project project);
    List<Project> getAllProjects();
    PageDto<ProjectDto> getAllSortedProjects(TableSortParametersDTO parameters);
    void deleteProject(String id);
    void addAssigner(String projectId,String userId);
    void deleteAssigner(String projectId, String userId);
}
