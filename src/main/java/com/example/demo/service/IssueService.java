package com.example.demo.service;

import com.example.demo.model.Issue;

import java.util.List;

public interface IssueService {
    Issue createIssue(String projectId, Issue issue);
    Issue getIssueById(String id);
    Issue updateIssue(Issue updateIssueDto);
    List<Issue> getAllIssues();
    void deleteIssue(String id);
}
