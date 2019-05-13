package com.example.demo.service.impl;

import com.example.demo.dto.IssueDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Issue;
import com.example.demo.repository.IssueRepository;
import com.example.demo.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private IssueRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public IssueServiceImpl(IssueRepository repository,ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public Issue saveIssue(IssueDto issueDto) {
        Issue issue = modelMapper.map(issueDto,Issue.class);
        //issue.setIssueStatus(IssueStatus.Open);
        //issue.setIssueType(IssueType.Feature);
        //issue.setIssuePriority(IssuePriority.Medium);
        return repository.save(issue);
    }

    @Override
    public Issue getIssueById(String id) {
        return repository.findIssueById(id);
    }

    @Override
    public List<Issue> getIssuesByProjectId(String id) {
        return repository.findIssuesByProjectId(id);
    }

    @Override
    public Issue updateIssue(Issue issue) {
        return repository.save(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return repository.findAll();
    }

    @Override
    public void deleteIssue(String id) {
        repository.deleteById(id);
    }

    @Override
    public PageDto<IssueDto> getSortedIssuesByProjectId(String id, TableSortParametersDTO parametersDTO){
        PageDto<IssueDto> pageDto = new PageDto<>();

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
