package com.example.demo.service.impl;

import com.example.demo.model.Issue;
import com.example.demo.model.IssuePriority;
import com.example.demo.model.IssueStatus;
import com.example.demo.model.IssueType;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;

    @Autowired
    public IssueServiceImpl (IssueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Issue saveIssue(Issue issue) {
        Date d = new Date();
        issue.setStartDate(new java.sql.Date(d.getTime()));
        return repository.save(issue);
    }

    @Override
    public Issue getIssueById (String id) {
        return repository.findIssueById(id);
    }

    @Override
    public Issue updateIssue (Issue issue) {
        Issue dbIssue = repository.findById (issue.getId ()).orElseThrow (InternalError::new);
        issue.setStartDate (dbIssue.getStartDate ());

//        if (!issue.getProject().getId().equals(dbIssue.getProject().getId())
//                || !issue.getReporter().getId().equals(dbIssue.getReporter().getId())) {
//            throw new InternalError();
//        }

        return repository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues () {
        return (List<Issue>) repository.findAll ();
    }

    @Override
    public void deleteIssue (String id) {
        repository.deleteById (id);
    }
}
