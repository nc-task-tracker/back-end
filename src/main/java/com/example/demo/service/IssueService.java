package com.example.demo.service;

import com.example.demo.model.Filter;
import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Issue;

import java.util.Collection;

import com.example.demo.model.ModelForSearch;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface IssueService {
    Issue createIssue(String projectId, String assigneeId, String reporterId, Issue issue);
    Issue getIssueById(String id);
    Issue updateIssue(Issue updateIssueDto);
    List<Issue> getAllIssues();
    List<Issue> getIssuesByProjectId(String id);
    void deleteIssue(String id);
    PageDto<IssueDto> getSortedIssuesByProjectId(String id, TableSortParametersDTO parametersDTO);
    List<Issue> searchIssue(Filter filter);
    List<Issue> getIssueName(String inputValue);
//    List<ModelForSearch> searchAssignee(String inputValue);
//    List<ModelForSearch> searchReporter(String inputValue);
}
