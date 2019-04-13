package com.example.demo.service.impl;

import com.example.demo.model.ProjectType;
import com.example.demo.repository.ProjectTypeRepository;
import com.example.demo.service.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

    private ProjectTypeRepository repository;

    @Autowired
    public ProjectTypeServiceImpl(ProjectTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectType saveProjectType(ProjectType projectType) {
        return repository.save(projectType);
    }

    @Override
    public ProjectType getProjectTypeById(String id) {
        return repository.findProjectTypeById(id);
    }

    @Override
    public ProjectType updateProjectType(ProjectType projectType) {
        return repository.save(projectType);
    }

    @Override
    public List<ProjectType> getAllProjectTypes() {
        return (List<ProjectType>) repository.findAll();
    }

    @Override
    public void deleteProjectType(String id) {
        repository.deleteById(id);
    }
}
