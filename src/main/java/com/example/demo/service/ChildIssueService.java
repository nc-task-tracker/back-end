package com.example.demo.service;

import com.example.demo.model.ChildIssue;

import java.util.List;

public interface ChildIssueService {
    ChildIssue saveChildIssue(ChildIssue childIssue);
    ChildIssue getChildIssueById(String id);
    ChildIssue updateChildIssue(ChildIssue childIssue);
    List<ChildIssue> getAllChildIssues();
    void deleteChildIssue(String id);
}
