package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.service.IssueService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;
    private ProfileRepository profileRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository repository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
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
    public List<Issue> searchIssue(Filter filter) {
        return (List<Issue>) repository.findAll(createIssueSearchPredicate(filter));
    }

    @Override
    public List<Issue> getIssueName(String inputValue) {
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(1, 10, sort);
        List<Issue> resultSearch = StringUtils.isEmpty(inputValue) ?
                repository.findAll(sort)
                : repository.findIssueNameBySubstring(String.format("%%%s%%", inputValue), sort);

        return resultSearch.stream()
                .map(item -> new Issue(item.getName() == null ? null : item.getName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    //todo: fix add parameters
    public Predicate createIssueSearchPredicate(Filter filterForSearch){
        BooleanBuilder expression = new BooleanBuilder();
        QIssue issue = QIssue.issue;
        QProfile profile = QProfile.profile;

        filterForSearch.getParameters().forEach(parameter -> {
            switch (parameter.getParameterType()){
                case ISSUE_NAME:
                    expression.and(issue.name.stringValue().in(parameter.getParameterValue()));
                    break;
                case ISSUE_TYPE:
                    if(parameter.getParameterValues()!=null) {
                            expression.and(issue.issuetype.stringValue().in(parameter.getParameterValues()));
                    }
                    break;
                case ISSUE_STATUS:
                    if(parameter.getParameterValues()!=null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.issuestatus.stringValue().in(val));
                        });
                    }
                    break;
                case ISSUE_PRIORITY:
                    if(parameter.getParameterValues()!=null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.issuepriority.stringValue().in(val));
                        });
                    }
                    break;
                case ASSIGNEE:
                    if(parameter.getParameterValues()!=null) {
                        parameter.getParameterValues().forEach(val -> {
//                            expression.and(issue.assignee.firstName.in(val).or(expression.and(profile.user.login.in(val))));
                            expression.and(issue.assignee.firstName.in(val));
                        });
                    }
//                    expression.and(issue.assignee.firstName.in(parameter.getParameterValue()));
                    break;
                case REPORTER:
                    if(parameter.getParameterValues()!=null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.reporter.firstName.in(val));
//                            expression.and(issue.reporter.firstName.in(val).or(expression.and(profile.user.login.in(val))));
                        });
                    }
//                    expression.and(issue.reporter.firstName.in(parameter.getParameterValue()));
                    break;
                case DUE_DATE:
                    expression.and(issue.dueDate.stringValue().in(parameter.getParameterValue()));
                    break;
                case START_DATE:
                    expression.and(issue.startDate.stringValue().in(parameter.getParameterValue()));
                    break;
                case PROJECT_NAME:
                    if(parameter.getParameterValues()!=null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.project.projectName.stringValue().in(val));
                        });
                    }
//                    expression.and(issue.project.projectName.in(parameter.getParameterValue()));
                    break;
                case ISSUE_DESCRIPTION:
                    expression.and(issue.description.in(parameter.getParameterValues()));
                    break;
            }
        });
        return expression;
    }

}
