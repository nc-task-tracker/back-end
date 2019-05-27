package com.example.demo.service;

import com.example.demo.model.Filter;
import com.example.demo.model.Issue;

import java.util.Collection;

import com.example.demo.model.ModelForSearch;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface IssueService {
    Issue saveIssue(Issue issue);
    Issue getIssueById(String id);
    Issue updateIssue(Issue issue);
    List<Issue> getAllIssues();
    void deleteIssue(String id);
    List<Issue> searchIssue(Filter filter);
    List<Issue> getIssueName(String inputValue);
//    List<ModelForSearch> searchAssignee(String inputValue);
//    List<ModelForSearch> searchReporter(String inputValue);
}
