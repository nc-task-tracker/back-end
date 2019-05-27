package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String>, QuerydslPredicateExecutor<Project> {
    Project findProjectById(String id);
    Project findProjectByProjectName(String name);
    Project findProjectByProjectCode(String projectCode);
    Page<Project> findAll(Pageable pageable);

//    @Query(value = "insert into new_schema.project_assigner(project_id,user_id) " +
//            "values (:projectId,:userId)",nativeQuery = true)
//    void addAssigner(@PathVariable(name = "projectId") String projectId,
//                     @PathVariable(name = "userId") String userId);
//
//    @Query(value = "delete from project_assigner pa where pa.project_id=:projectId" +
//            " and pa.user_id=:userId",nativeQuery = true)
//    void deleteAssigner(@PathVariable(name = "projectId") String projectId,
//                        @PathVariable(name = "userId") String userId);

    @Query("select pr from Project pr where upper(pr.projectName) like upper(?1)")
    List<Project> findProjectBySubstring(String substring, Sort sort);
}
