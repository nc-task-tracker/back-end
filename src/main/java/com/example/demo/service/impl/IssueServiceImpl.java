package com.example.demo.service.impl;

import com.example.demo.model.Issue;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public List<Issue> searchIssue(Issue issue) {
        return (List<Issue>) repository.findAll(createIssueSearchPredicate(issue));
    }

    public Predicate createIssueSearchPredicate(Issue issueForSearch){
        BooleanBuilder expression = new BooleanBuilder();

//        QIssue issue = QIssue.issue;
//
//        if(issueForSearch.getId() != null) {
//            expression.and(issue.id.likeIgnoreCase(issueForSearch.getId()));
//        }
//        if (issueForSearch.getIssueName() != null) {
//            expression.and(issue.issueName.likeIgnoreCase(issueForSearch.getIssueName()));
//        }
//        if (issueForSearch.getParentId() != null) {
//            expression.and(issue.parentId.likeIgnoreCase(issueForSearch.getParentId()));
//        }

        return expression;
    }

}
