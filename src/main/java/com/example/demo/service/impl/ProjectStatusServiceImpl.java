package com.example.demo.service.impl;

import com.example.demo.model.ProjectStatus;
import com.example.demo.repository.ProjectStatusRepository;
import com.example.demo.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {

    private ProjectStatusRepository repository;

    @Autowired
    public ProjectStatusServiceImpl(ProjectStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectStatus saveProjectStatus(ProjectStatus projectStatus) {
        return repository.save(projectStatus);
    }

    @Override
    public ProjectStatus getProjectStatusById(String id) {
        return repository.findProjectStatusById(id);
    }

    @Override
    public ProjectStatus updateProjectStatus(ProjectStatus projectStatus) {
        return repository.save(projectStatus);
    }

    @Override
    public List<ProjectStatus> getAllProjectStatuses() {
        return (List<ProjectStatus>) repository.findAll();
    }

    @Override
    public void deleteProjectStatus(String id) {
        repository.deleteById(id);
    }
}
