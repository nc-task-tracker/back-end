package com.example.demo.service.impl;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectStatus;
import com.example.demo.model.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;
    private ModelMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository,ModelMapper modelMapper) {
        this.repository = repository;
        this.mapper = modelMapper;
    }

    @Override
    public Project saveProject(Project project) {
        project.setProjectStatus(ProjectStatus.OPEN);
        return repository.save(project);
    }

    @Override
    public Project getProjectById(String id) {
        return repository.findProjectById(id);
    }

    @Override
    public Project updateProject(Project project) {
        return repository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public PageDto<ProjectDto> getAllSortedProjects(TableSortParametersDTO parameters) {
        PageDto<ProjectDto> pageDto = new PageDto<>();

        Sort sort = new Sort(parameters.get_direction().equals("asc")? Sort.Direction.ASC: Sort.Direction.DESC,
                parameters.get_columnName());
        Pageable pageable = PageRequest.of(parameters.get_page(),parameters.get_maxElemOnPage(),sort);

        Page<Project> page = repository.findAll(pageable);

        pageDto.setList(page.get().map(value -> mapper.map(value,ProjectDto.class))
                .collect(Collectors.toList()));
        pageDto.setTotalElem(page.getTotalElements());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setPageSize(page.getSize());

        return pageDto;
    }

    @Override
    public void deleteProject(String id) {
        repository.deleteById(id);
    }

    @Override
    public void addAssigner(String projectId,String userId){
        repository.addAssigner(projectId,userId);
    }

    @Override
    public void deleteAssigner(String projectId,String userId) {
        repository.deleteAssigner(projectId,userId);
    }
}
