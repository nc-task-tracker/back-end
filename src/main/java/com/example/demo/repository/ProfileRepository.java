package com.example.demo.repository;

import com.example.demo.model.Issue;
import com.example.demo.model.ModelForSearch;
import com.example.demo.model.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, String>,
        JpaRepository<Profile, String> {
    Profile findProfileById(String id);

    @Query(value = "SELECT * FROM Profile pr inner join Issue iss On pr.id = iss.assignee_id " +
            "where upper(pr.first_name) like  upper(?1)",
    nativeQuery = true)
    List<Profile> searchAssignee(String substring);

    @Query(value = "SELECT * FROM Profile pr inner join Issue iss On pr.id = iss.reporter_id " +
            "where upper(pr.first_name) like  upper(?1)",
    nativeQuery = true)
    List<Profile> searchReporter(String substring);
}
