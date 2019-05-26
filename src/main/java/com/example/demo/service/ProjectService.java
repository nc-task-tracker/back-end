package com.example.demo.service;

import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.model.projectFilter.ParameterProject;
import com.example.demo.model.projectFilter.ProjectFilter;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);
    Project getProjectById(String id);
    Project updateProject(Project project);
    List<Project> getAllProjects();
    PageDto<ProjectDto> getAllSortedProjects(TableSortParametersDTO parameters);
    void deleteProject(String id);
    Project getProjectByName(String name);
    Project getProjectByCode(String code);
    List<Project> searchProject(ProjectFilter projectFilter);
    //void addAssigner(String code,String userId);
    //void deleteAssigner(String code, String userId);
    List<ProjectMemberDto> getProjectMembers(String id);
}
