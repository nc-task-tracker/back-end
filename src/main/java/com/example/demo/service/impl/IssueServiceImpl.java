package com.example.demo.service.impl;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Identificator;
import com.example.demo.model.Issue;
import org.modelmapper.ModelMapper;
import com.example.demo.model.IssueStatus;
import com.example.demo.model.Project;
import com.example.demo.repository.IdentificatorRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.IssueService;
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

    private IssueRepository repository;
    private ModelMapper modelMapper;

    private final IdentificatorRepository idRepository;

    private final ProjectRepository projectRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository repository,
                            IdentificatorRepository idRepository,
                            ProjectRepository projectRepository) {
        this.repository = repository;
        this.idRepository = idRepository;
        this.projectRepository = projectRepository;
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
        issue.setIssueCode (issueCode);
        issue.setIssueStatus(IssueStatus.OPEN);
        return repository.save(issue);
    }

    @Override
    public Issue getIssueById (String id) {
        return repository.findIssueById(id);
    }

    @Override
    public List<Issue> getIssuesByProjectId(String id) {
        return repository.findIssuesByProjectId(id);
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
    public List<Issue> getAllIssues() {
        return repository.findAll();
    }

    @Override
    public void deleteIssue (String id) {
        repository.deleteById (id);
    }

    @Override
    public PageDto<IssueDto> getSortedIssuesByProjectId(String id, TableSortParametersDTO parametersDTO){
        PageDto<IssueDto> pageDto = new PageDto<>();
        System.out.println(parametersDTO.toString());
        System.out.println(id);

        Sort sort = new Sort(parametersDTO.get_direction().equals("asc")? Sort.Direction.ASC:Sort.Direction.DESC,
                parametersDTO.get_columnName());
        Pageable pageable = PageRequest.of(parametersDTO.get_page(),parametersDTO.get_maxElemOnPage(),sort);

        Page<Issue> all = repository.findAllByProjectId(id,pageable);

        pageDto.setTotalElem(all.getTotalElements());
        pageDto.setTotalPages(all.getTotalPages());
        pageDto.setList(all.get().map(value -> modelMapper.map(value,IssueDto.class)).collect(Collectors.toList()));
        pageDto.setPageSize(all.getSize());

        return pageDto;
    }
}
