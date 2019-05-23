package com.example.demo.service.impl;

import com.example.demo.model.Identificator;
import com.example.demo.model.Issue;
import com.example.demo.model.IssueStatus;
import com.example.demo.model.Project;
import com.example.demo.repository.IdentificatorRepository;
import com.example.demo.repository.IssueRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;

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
        issue.setCode(issueCode);
        issue.setIssueStatus(IssueStatus.OPEN);
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
