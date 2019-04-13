package com.example.demo.service;

import com.example.demo.model.IssueType;

import java.util.List;

public interface IssueTypeService {
    IssueType saveIssueType(IssueType issueType);
    IssueType getIssueTypeById(String id);
    IssueType updateIssueType(IssueType issueType);
    List<IssueType> getAllIssueTypes();
    void deleteIssueType(String id);
}
