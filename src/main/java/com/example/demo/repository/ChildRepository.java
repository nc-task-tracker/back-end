package com.example.demo.repository;

import com.example.demo.model.ChildIssue;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<ChildIssue, String> {
    ChildIssue findChildIssueById(String id);
}
