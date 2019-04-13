package com.example.demo.service;

import com.example.demo.model.IssueStatus;

import java.util.List;

public interface IssueStatusService {
    IssueStatus saveIssueStatus(IssueStatus issueStatus);
    IssueStatus getIssueStatusById(String id);
    IssueStatus updateIssueStatus(IssueStatus issueStatus);
    List<IssueStatus> getAllIssueStatuses();
    void deleteIssueStatus(String id);
}
