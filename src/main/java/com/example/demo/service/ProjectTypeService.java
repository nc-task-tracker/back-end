package com.example.demo.service;

import com.example.demo.model.ProjectType;

import java.util.List;

public interface ProjectTypeService {
    ProjectType saveProjectType(ProjectType projectType);
    ProjectType getProjectTypeById(String id);
    ProjectType updateProjectType(ProjectType projectType);
    List<ProjectType> getAllProjectTypes();
    void deleteProjectType(String id);
}
