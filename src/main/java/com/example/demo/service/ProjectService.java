package com.example.demo.service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Project;
import com.example.demo.model.projectFilter.ParameterProject;
import com.example.demo.model.projectFilter.ProjectFilter;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);
    Project getProjectById(String id);
    Project updateProject(Project project);
    List<Project> getAllProjects();
    void deleteProject(String id);
    Project getProjectByName(String name);
    Project getProjectByCode(String code);
    List<Project> searchProject(ProjectFilter projectFilter);
    List<Project> getProjectsBySubstring(String name);
}
