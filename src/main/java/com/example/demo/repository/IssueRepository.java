package com.example.demo.repository;

import com.example.demo.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String>,QuerydslPredicateExecutor<Issue> {
    Issue findIssueById(String id);
    List<Issue> findIssuesByProjectId(String id);
    Page<Issue> findAllByProjectId(String id, Pageable pageable);

    @Query("select iss from Issue iss where upper(iss.issueName) like upper(?1)")
    List<Issue> findIssueNameBySubstring(String substring, Sort sort);

    void deleteIssueById(String id);
//
//    @Query(" select pr.firstName from Profile as pr join issue as i on i.assigne = pr.id where upper(pr.firstName) like upper(?1)" )
//    @Query("SELECT profile.first_name\n" +
//            "FROM profile inner join\n" +
//            "         issue On new_schema.profile.id = issue.assignee_id\n" +
//            "where upper(profile.first_name) like  upper(?1)")
//    List<ModelForSearch> searchByName(String substring, Sort sort);


}
