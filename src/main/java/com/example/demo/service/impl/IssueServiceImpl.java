package com.example.demo.service.impl;

import com.example.demo.model.Identificator;
import com.example.demo.model.*;
import com.example.demo.repository.IdentificatorRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Issue;
import com.example.demo.repository.*;
import com.example.demo.model.IssueStatus;
import com.example.demo.model.Project;
import com.example.demo.service.IssueService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private IssueRepository issueRepository;
    private ModelMapper modelMapper;
    private ProfileRepository profileRepository;

    private final IdentificatorRepository idRepository;

    private final ProjectRepository projectRepository;

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository,
                            UserRepository userRepository,
                            ProfileRepository profileRepository,
                            IdentificatorRepository idRepository,
                            ProjectRepository projectRepository,
                            ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.idRepository = idRepository;
        this.profileRepository = profileRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;

    }

    @Transactional
    @Override
    public Issue createIssue(String projectId, String assigneeId, String reporterId, Issue issue) {
        Date d = new Date();
        Project project = projectRepository.findProjectById(projectId);
        issue.setProject(project);


        String parentProjectCode = issue.getProject().getProjectCode();
        Identificator identificator = idRepository.findIdentificatorById(1236751267);
        String issueCode = String.format("%s-%d", parentProjectCode, identificator.getCurFreedom());
        identificator.setCurFreedom(identificator.getCurFreedom() + 1);
        idRepository.save(identificator);
        issue.setStartDate(new java.sql.Date(d.getTime()));
        issue.setAssignee(userRepository.findUserById(assigneeId).getProfile());
        issue.setReporter(userRepository.findUserById(reporterId).getProfile());
        issue.setIssueCode (issueCode);
//        issue.setIssueStatus(IssueStatus.OPEN);

        return issueRepository.save(issue);
    }

    @Override
    public Issue getIssueById(String id) {
        return issueRepository.findIssueById(id);
    }

    @Override
    public List<Issue> getIssuesByProjectId(String id) {
        return issueRepository.findIssuesByProjectId(id);
    }

    @Override
    public Issue updateIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    @Override
    public void deleteIssue(String id) {
        issueRepository.deleteById(id);
    }

    @Override
    public PageDto<IssueDto> getSortedIssuesByProjectId(String id, TableSortParametersDTO parametersDTO) {
        PageDto<IssueDto> pageDto = new PageDto<>();

        Sort sort = new Sort(parametersDTO.get_direction().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                parametersDTO.get_columnName());
        Pageable pageable = PageRequest.of(parametersDTO.get_page(), parametersDTO.get_maxElemOnPage(), sort);

        Page<Issue> all = issueRepository.findAllByProjectId(id, pageable);

        pageDto.setTotalElem(all.getTotalElements());
        pageDto.setTotalPages(all.getTotalPages());
        pageDto.setList(all.get().map(value -> modelMapper.map(value, IssueDto.class)).collect(Collectors.toList()));
        pageDto.setPageSize(all.getSize());

        return pageDto;
    }

//    @Override
//    public List<ModelForSearch> searchAssignee(String inputValue) {
//        Sort sort = new Sort(Sort.Direction.ASC, "first_name");
//        Pageable pageable = PageRequest.of(1, 10, sort);
//        List<Profile> resultSearch = StringUtils.isEmpty(inputValue) ?
//                profileRepository.findAll(sort)
//                : profileRepository.searchAssignee(String.format("%%%s%%", inputValue));
//
//        return resultSearch.stream()
//                .map(item -> new ModelForSearch(item.getId(), item.getFirstName() == null ? null : item.getFirstName()))
//                .collect(Collectors.toCollection(LinkedList::new));
//    }

//    @Override
//    public List<ModelForSearch> searchReporter(String inputValue) {
//        Sort sort = new Sort(Sort.Direction.ASC, "first_name");
//        Pageable pageable = PageRequest.of(1, 10, sort);
//        List<Profile> resultSearch = StringUtils.isEmpty(inputValue) ?
//                profileRepository.findAll(sort)
//                : profileRepository.searchReporter(String.format("%%%s%%", inputValue));
//
//        return resultSearch.stream()
//                .map(item -> new ModelForSearch(item.getId(), item.getFirstName() == null ? null : item.getFirstName()))
//                .collect(Collectors.toCollection(LinkedList::new));
//    }

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
//                .map(item -> new Issue(item.getIssueName() == null ? null : item.getIssueName()))
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
                    expression.and(issue.issueName.stringValue().in(parameter.getParameterValue()));
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
                            expression.and(issue.assignee.fullName.in(val));
                        });
                    }
//                    expression.and(issue.assignee.firstName.in(parameter.getParameterValue()));
                    break;
                case REPORTER:
                    if(parameter.getParameterValues()!=null) {
                        parameter.getParameterValues().forEach(val -> {
                            expression.and(issue.reporter.fullName.in(val));
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
                    expression.and(issue.issueDescription.in(parameter.getParameterValues()));
                    break;
            }
        });
        return expression;
    }

}
