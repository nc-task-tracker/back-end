package com.example.demo.repository;

import com.example.demo.model.Issue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, String> {
    Issue findIssueById(String id);
    List<Issue> findIssuesByProjectId(String id);
    List<Issue> findIssuesByProjectId(String id, Pageable pageable);
}
