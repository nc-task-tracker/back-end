package com.example.demo.service.impl;

import com.example.demo.model.IssueRole;
import com.example.demo.repository.IssueRoleRepository;
import com.example.demo.service.IssueRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueRoleServiceImpl implements IssueRoleService {

    private IssueRoleRepository repository;

    @Autowired
    public IssueRoleServiceImpl(IssueRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssueRole saveIssueRole(IssueRole issueRole) {
        return repository.save(issueRole);
    }

    @Override
    public IssueRole getIssueRoleById(String id) {
        return repository.findIssueRoleById(id);
    }

    @Override
    public IssueRole updateIssueRole(IssueRole issueRole) {
        return repository.save(issueRole);
    }

    @Override
    public List<IssueRole> getAllIssueRoles() {
        return (List<IssueRole>) repository.findAll();
    }

    @Override
    public void deleteIssueRole(String id) {
        repository.deleteById(id);
    }
}
