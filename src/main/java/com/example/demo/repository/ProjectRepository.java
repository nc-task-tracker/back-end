package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String>/*, QuerydslPredicateExecutor<Project>*/ {
    Project findProjectById(String id);
    Project findProjectByProjectName(String name);
    Project findProjectByProjectCode(String projectCode);
}
