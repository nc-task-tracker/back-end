package com.example.demo.service.impl;

import com.example.demo.model.Identificator;
import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Issue;
import com.example.demo.repository.*;
import com.example.demo.model.IssueStatus;
import com.example.demo.model.Project;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository issueRepository;
    private ModelMapper modelMapper;

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
        issue.setCode(issueCode);
        issue.setIssueStatus(IssueStatus.OPEN);

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
}
