package com.example.demo.repository;

import com.example.demo.model.ProjectStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStatusRepository extends CrudRepository<ProjectStatus, String> {
    ProjectStatus findProjectStatusById(String id);
}
