package com.example.demo.service.impl;

import com.example.demo.dto.IssueDto;
import com.example.demo.model.Issue;
import com.example.demo.model.IssuePriority;
import com.example.demo.model.IssueStatus;
import com.example.demo.model.IssueType;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public IssueServiceImpl(IssueRepository repository,ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public Issue saveIssue(IssueDto issueDto) {
        Issue issue = modelMapper.map(issueDto,Issue.class);
        //issue.setIssueStatus(IssueStatus.Open);
        //issue.setIssueType(IssueType.Feature);
        //issue.setIssuePriority(IssuePriority.Medium);
        return repository.save(issue);
    }

    @Override
    public Issue getIssueById(String id) {
        return repository.findIssueById(id);
    }

    @Override
    public List<Issue> getIssuesByProjectId(String id) {
        return repository.findIssuesByProjectId(id);
    }

    @Override
    public Issue updateIssue(Issue issue) {
        return repository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return (List<Issue>) repository.findAll();
    }

    @Override
    public void deleteIssue(String id) {
        repository.deleteById(id);
    }
}
