package com.example.demo.service.impl;

import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectStatus;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.mappers.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper projectMapper) {
        this.repository = repository;
        this.projectMapper = projectMapper;
    }

    @Override
    public Project createProject(Project project) {
        Project temp = repository.findProjectByProjectName(project.getProjectName());
        if (temp == null) {
            project.setProjectstatus(ProjectStatus.OPEN);

            return repository.save(project);
        } else return null;
    }

    @Override
    public Project getProjectById(String id) {
        return repository.findProjectById(id);
    }

    @Override
    public Project updateProject(Project project) {
        return repository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return (List<Project>)repository.findAll();
    }

    @Override
    public void deleteProject(String id) {
        repository.deleteById(id);
    }
}
