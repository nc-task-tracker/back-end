package com.example.demo.service.impl;

import com.example.demo.model.Identificator;
import com.example.demo.model.*;
import com.example.demo.repository.IdentificatorRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.IssueService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.apache.commons.lang3.StringUtils;

import javax.transaction.Transactional;
import java.util.LinkedList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;
    private ProfileRepository profileRepository;

    private final IdentificatorRepository idRepository;

    private final ProjectRepository projectRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository repository,
                            IdentificatorRepository idRepository,
                            ProjectRepository projectRepository) {
        this.repository = repository;
        this.idRepository = idRepository;
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    @Override
    public Issue createIssue(String projectId, Issue issue) {
        Date d = new Date();
        Project project = projectRepository.findProjectById(projectId);
        issue.setProject(project);

        String parentProjectCode = issue.getProject().getProjectCode();
        Identificator identificator = idRepository.findIdentificatorById(1236751267);
        String issueCode = String.format("%s-%d", parentProjectCode, identificator.getCurFreedom());
        identificator.setCurFreedom(identificator.getCurFreedom()+1);
        idRepository.save(identificator);
        issue.setStartDate(new java.sql.Date(d.getTime()));
        issue.setCode(issueCode);
        issue.setIssuestatus(IssueStatus.OPEN);
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

    @Override
    public List<Issue> searchIssue(Filter filter) {
        return (List<Issue>) repository.findAll(createIssueSearchPredicate(filter));
    }

    @Override
    public List<Issue> getIssueName(String inputValue) {
        Sort sort = new Sort(Sort.Direction.ASC, "issueName");
        Pageable pageable = PageRequest.of(1, 10, sort);
        List<Issue> resultSearch = StringUtils.isEmpty(inputValue) ?
                repository.findAll(sort)
                : repository.findIssueNameBySubstring(String.format("%%%s%%", inputValue), sort);

        return resultSearch.stream()
                .map(item -> new Issue(item.getIssueName() == null ? null : item.getIssueName()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    //todo: fix add parameters
    public Predicate createIssueSearchPredicate(Filter filterForSearch){
        BooleanBuilder expression = new BooleanBuilder();
        QIssue issue = QIssue.issue;
        QProfile profile = QProfile.profile;
        QUser user = QUser.user;

        filterForSearch.getParameters().forEach(parameter -> {
            switch (parameter.getParameterType()){
                case ISSUE_NAME:
                    if(parameter.getParameterValues() != null) {
                        expression.and(issue.issueName.in(parameter.getParameterValues()));
                    }break;
                case ISSUE_TYPE:
                    if(parameter.getParameterValues() != null) {
                        expression.and(issue.issuetype.stringValue().in(parameter.getParameterValues()));
                    }
                    break;
                case ISSUE_STATUS:
                    if(parameter.getParameterValues() != null) {
                        expression.and(issue.issuestatus.stringValue().in(parameter.getParameterValues()));
                    }
                    break;
                case ISSUE_PRIORITY:
                    if(parameter.getParameterValues() != null) {
                        expression.and(issue.issuepriority.stringValue().in(parameter.getParameterValues()));
                    }
                    break;
                case ASSIGNEE:
                    if(parameter.getParameterValues() != null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.assignee.fullName.like("%" + val + "%").or(issue.assignee.user.login.like("%" + val + "%")));
                        });
                    }
                    break;
                case REPORTER:
                    if(parameter.getParameterValues() != null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.reporter.fullName.in(val));
//                            expression.and(issue.reporter.firstName.in(val).or(expression.and(profile.user.login.in(val))));
                        });
                    }
                    break;
                case PROJECT_NAME:
                    if(parameter.getParameterValues()!= null) {
                        expression.and(issue.project.projectName.stringValue().like("%" + parameter.getParameterValues() + "%"));
                    }
                    break;
            }
        });
        return expression;
    }
}
