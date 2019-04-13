package com.example.demo.service.impl;

import com.example.demo.model.ChildIssue;
import com.example.demo.repository.ChildIssueRepository;
import com.example.demo.service.ChildIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildIssueServiceImpl implements ChildIssueService {

    private ChildIssueRepository repository;

    @Autowired
    public ChildIssueServiceImpl(ChildIssueRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChildIssue saveChildIssue(ChildIssue childIssue) {
        return repository.save(childIssue);
    }

    @Override
    public ChildIssue getChildIssueById(String id) {
        return repository.findChildIssueById(id);
    }

    @Override
    public ChildIssue updateChildIssue(ChildIssue childIssue) {
        return repository.save(childIssue);
    }

    @Override
    public List<ChildIssue> getAllChildIssues() {
        return (List<ChildIssue>) repository.findAll();
    }

    @Override
    public void deleteChildIssue(String id) {
        repository.deleteById(id);
    }
}
