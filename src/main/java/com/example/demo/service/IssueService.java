package com.example.demo.service;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.TableSortParametersDTO;
import com.example.demo.model.Issue;

import java.util.List;

public interface IssueService {
    Issue saveIssue(IssueDto issue);
    Issue getIssueById(String id);
    Issue updateIssue(Issue issue);
    List<Issue> getAllIssues();
    List<Issue> getIssuesByProjectId(String id);
    void deleteIssue(String id);
    List<Issue> getSortedIssuesByProjectId(String id, TableSortParametersDTO parametersDTO);
}
