package com.example.demo.repository;

import com.example.demo.model.ChildIssue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildIssueRepository extends CrudRepository<ChildIssue, String> {
    ChildIssue findChildIssueById(String id);
}

