package com.example.demo.repository;

import com.example.demo.model.IssueStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueStatusRepository extends CrudRepository<IssueStatus, String> {
    IssueStatus findIssueStatusById(String id);
}
