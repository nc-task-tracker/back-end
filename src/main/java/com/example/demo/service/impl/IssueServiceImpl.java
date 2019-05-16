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
//        issue.setStartDate(new Date());
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
    public List<ModelForSearch> searchAssignee(String inputValue) {
        Sort sort = new Sort(Sort.Direction.ASC, "first_name");
        Pageable pageable = PageRequest.of(1, 10, sort);
        List<Profile> resultSearch = StringUtils.isEmpty(inputValue) ?
                profileRepository.findAll(sort)
                : profileRepository.searchAssignee(String.format("%%%s%%", inputValue));

        return resultSearch.stream()
                .map(item -> new ModelForSearch(item.getId(), item.getFirstName() == null ? null : item.getFirstName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<ModelForSearch> searchReporter(String inputValue) {
        Sort sort = new Sort(Sort.Direction.ASC, "first_name");
        Pageable pageable = PageRequest.of(1, 10, sort);
        List<Profile> resultSearch = StringUtils.isEmpty(inputValue) ?
                profileRepository.findAll(sort)
                : profileRepository.searchReporter(String.format("%%%s%%", inputValue));

        return resultSearch.stream()
                .map(item -> new ModelForSearch(item.getId(), item.getFirstName() == null ? null : item.getFirstName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Issue> searchIssue(Filter filter) {
        return (List<Issue>) repository.findAll(createIssueSearchPredicate(filter));
    }

    public Predicate createIssueSearchPredicate(Filter filterForSearch){
        BooleanBuilder expression = new BooleanBuilder();
        QIssue issue = QIssue.issue;

        filterForSearch.getParameters().forEach(parameter -> {
            switch (parameter.getParameterType()){
                case ISSUE_NAME:
                    expression.and(issue.issueName.stringValue().in(parameter.getParameterValue()));
                    break;
                case ISSUE_TYPE:
                    expression.and(issue.issuetype.stringValue().in(parameter.getParameterValues()));
                    break;
                case ISSUE_STATUS:
                    expression.and(issue.issuestatus.stringValue().in(parameter.getParameterValues()));
                    break;
                case ISSUE_PRIORITY:
                    expression.and(issue.issuepriority.stringValue().in(parameter.getParameterValues()));
                    break;
                case ASSIGNER:
                    expression.and(issue.assignee.firstName.in(parameter.getParameterValue()));
                    break;
                case DUE_DATE:
                    expression.and(issue.dueDate.stringValue().in(parameter.getParameterValue()));
                    break;
                case REPORTER:
                    expression.and(issue.reporter.firstName.in(parameter.getParameterValue()));
                    break;
                case START_DATE:
                    expression.and(issue.startDate.stringValue().in(parameter.getParameterValue()));
                    break;
                case PROJECT_NAME:
                    expression.and(issue.project.projectName.in(parameter.getParameterValue()));
                    break;
                case ISSUE_DESCRIPTION:
                    expression.and(issue.issueDescription.in(parameter.getParameterValues()));
                    break;
            }
        });
        return expression;
    }

}
