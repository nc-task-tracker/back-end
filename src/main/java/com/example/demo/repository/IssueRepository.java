package com.example.demo.repository;

import com.example.demo.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {
    Issue findIssueById(String id);
    List<Issue> findIssuesByProjectId(String id);
    Page<Issue> findAllByProjectId(String id, Pageable pageable);
}
