package com.example.demo.service.impl;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectStatus;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project saveProject(Project project) {
        Project temp = repository.findProjectByProjectName(project.getProjectName());
        Project temp1 = repository.findProjectByProjectCode(project.getProjectCode());
        if (temp == null && temp1 == null) {
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

    @Override
    public Project getProjectByName(String name) {
        return this.repository.findProjectByProjectName(name);
    }

    @Override
    public Project getProjectByCode(String code) {
        return this.repository.findProjectByProjectCode(code);
    }
}
