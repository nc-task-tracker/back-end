package com.example.demo.service.impl;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.*;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectStatus;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;
    private ModelMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.mapper = modelMapper;
    }

    @Override
    public Project createProject(Project project) {
        Project temp = repository.findProjectByProjectName(project.getProjectName());
        Project temp1 = repository.findProjectByProjectCode(project.getProjectCode());
        if (temp == null && temp1 == null) {
            project.setProjectStatus(ProjectStatus.OPEN);

            return repository.save(project);
        } else return null;
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

        Sort sort = new Sort(parameters.get_direction().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                parameters.get_columnName());
        Pageable pageable = PageRequest.of(parameters.get_page(), parameters.get_maxElemOnPage(), sort);

        Page<Project> page = repository.findAll(pageable);

        pageDto.setList(page.get().map(value -> mapper.map(value, ProjectDto.class))
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
    public Project getProjectByName(String name) {
        return this.repository.findProjectByProjectName(name);
    }

    @Override
    public Project getProjectByCode(String code) {
        return this.repository.findProjectByProjectCode(code);
    }

    @Override
    public List<Project> searchProject(ProjectFilter projectFilter) {
        return (List<Project>) repository.findAll(createProjectSearchPredicate(projectFilter));
    }

    @Override
    public List<ProjectMemberDto> getProjectMembers(String id) {

//        List<Profile> members = profileRepository.findProjectMembersByProjectId(id);
//
//        return members.stream().map(value ->{
//            ProjectMemberDto memberDto = new ProjectMemberDto();
//            memberDto.setMembers(new ArrayList<>());
//
//            memberDto.getMembers().add(value);
//            memberDto.setProjectRole(projectRoleRepository.findProjectMemberRoleByProjectIdAndProfileId(id,value.getId()));
//            return memberDto;
//        }).collect(Collectors.toList());
        return null;
    }

    @Override
    public List<Project> getProjectsBySubstring(String name) {


        Sort sort = new Sort(Sort.Direction.ASC, "projectName");
        Pageable pageable = PageRequest.of(1, 10, sort);
        List<Project> resultSearch = StringUtils.isEmpty(name) ?
                repository.findAll(sort)
                : repository.findProjectBySubstring(String.format("%%%s%%", name), sort);

        return resultSearch;
    }

}
