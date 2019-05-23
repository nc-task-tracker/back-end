package com.example.demo.service;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Issue;

import java.util.List;

public interface IssueService {
    Issue saveIssue(Issue issue);
    Issue getIssueById(String id);
    Issue updateIssue(Issue updateIssueDto);
    List<Issue> getAllIssues();
    List<Issue> getIssuesByProjectId(String id);
    void deleteIssue(String id);
    PageDto<IssueDto> getSortedIssuesByProjectId(String id, TableSortParametersDTO parametersDTO);
}
