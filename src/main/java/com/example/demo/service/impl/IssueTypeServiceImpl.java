package com.example.demo.service.impl;

import com.example.demo.model.IssueType;
import com.example.demo.repository.IssueTypeRepository;
import com.example.demo.service.IssueTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueTypeServiceImpl implements IssueTypeService {

    private IssueTypeRepository repository;

    @Autowired
    public IssueTypeServiceImpl(IssueTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssueType saveIssueType(IssueType issueType) {
        return repository.save(issueType);
    }

    @Override
    public IssueType getIssueTypeById(String id) {
        return repository.findIssueTypeById(id);
    }

    @Override
    public IssueType updateIssueType(IssueType issueType) {
        return repository.save(issueType);
    }

    @Override
    public List<IssueType> getAllIssueTypes() {
        return (List<IssueType>) repository.findAll();
    }

    @Override
    public void deleteIssueType(String id) {
        repository.deleteById(id);
    }
}
