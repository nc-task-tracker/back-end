package com.example.demo.service.impl;

import com.example.demo.model.IssueStatus;
import com.example.demo.repository.IssueStatusRepository;
import com.example.demo.service.IssueStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueStatusServiceImpl implements IssueStatusService {

    private IssueStatusRepository repository;

    @Autowired
    public IssueStatusServiceImpl(IssueStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssueStatus saveIssueStatus(IssueStatus issueStatus) {
        return repository.save(issueStatus);
    }

    @Override
    public IssueStatus getIssueStatusById(String id) {
        return repository.findIssueStatusById(id);
    }

    @Override
    public IssueStatus updateIssueStatus(IssueStatus issueStatus) {
        return repository.save(issueStatus);
    }

    @Override
    public List<IssueStatus> getAllIssueStatuses() {
        return (List<IssueStatus>) repository.findAll();
    }

    @Override
    public void deleteIssueStatus(String id) {
        repository.deleteById(id);
    }
}
