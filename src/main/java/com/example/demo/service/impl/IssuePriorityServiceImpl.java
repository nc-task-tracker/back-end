package com.example.demo.service.impl;

import com.example.demo.model.IssuePriority;
import com.example.demo.repository.IssuePriorityRepository;
import com.example.demo.service.IssuePriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuePriorityServiceImpl implements IssuePriorityService {

    private IssuePriorityRepository repository;

    @Autowired
    public IssuePriorityServiceImpl(IssuePriorityRepository repository) {
        this.repository = repository;
    }

    @Override
    public IssuePriority saveIssuePriority(IssuePriority issuePriority) {
        return repository.save(issuePriority);
    }

    @Override
    public IssuePriority getIssuePriorityById(String id) {
        return repository.findIssuePriorityById(id);
    }

    @Override
    public IssuePriority updateIssuePriority(IssuePriority issuePriority) {
        return repository.save(issuePriority);
    }

    @Override
    public List<IssuePriority> getAllIssuePriorities() {
        return (List<IssuePriority>) repository.findAll();
    }

    @Override
    public void deleteIssuePriority(String id) {
        repository.deleteById(id);
    }
}
