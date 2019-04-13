package com.example.demo.service.impl;

import com.example.demo.model.Issue;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;

    @Autowired
    public IssueServiceImpl(IssueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Issue saveIssue(Issue issue) {
        return repository.save(issue);
    }

    @Override
    public Issue getIssueById(String id) {
        return repository.findIssueById(id);
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
