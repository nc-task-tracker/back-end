package com.example.demo.repository;

import com.example.demo.model.Issue;
import com.example.demo.service.IssueService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<Issue, String>, QuerydslPredicateExecutor<Issue> {
    Issue findIssueById(String id);
}
