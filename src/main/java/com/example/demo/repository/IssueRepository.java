package com.example.demo.repository;

import com.example.demo.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {
    Issue findIssueById(String id);
}
