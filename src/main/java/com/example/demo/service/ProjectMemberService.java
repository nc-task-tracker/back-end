package com.example.demo.service;

import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    void deleteProjectMember(String projectId, String memberId,String token);
    List<ProfileDto> getPossibleProjectMembers(String id);
    ProjectMember addProjectMember(String id, ProjectMember member, String token);
    PageDto<ProjectMemberDto> getTablePageData(String id, TableSortParametersDTO parametersDTO);
}
