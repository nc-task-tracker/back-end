package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String>, QuerydslPredicateExecutor<Project> {
    Project findProjectById(String id);
    Project findProjectByProjectName(String name);
    Project findProjectByProjectCode(String projectCode);

    @Query("select pr from Project pr where upper(pr.projectName) like upper(?1)")
    List<Project> findProjectBySubstring(String substring, Sort sort);
}
