package com.example.demo.service.impl;

import com.example.demo.model.ChildIssue;
import com.example.demo.model.IssueStatus;
import com.example.demo.repository.ChildRepository;
import com.example.demo.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChildIssueServiceImpl implements ChildService {

    private ChildRepository childRepository;

    @Autowired
    public ChildIssueServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public ChildIssue saveChildIssue(ChildIssue childIssue) {
        childIssue.setIssueStatus (IssueStatus.OPEN);
        return childRepository.save(childIssue);
    }

    @Override
    public ChildIssue getChildIssueById(String id) {
        return childRepository.findChildIssueById (id);
    }

    @Override
    public ChildIssue updateChildIssue(ChildIssue childIssue) {
        return childRepository.save (childIssue);
    }

    @Override
    public List<ChildIssue> getAllChildIssues() {
        return (List<ChildIssue>) childRepository.findAll();
    }

    @Override
    public void deleteChildIssue(String id) {
        childRepository.deleteById(id);
    };
}
