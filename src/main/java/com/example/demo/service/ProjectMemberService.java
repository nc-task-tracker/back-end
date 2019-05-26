package com.example.demo.service;

import com.example.demo.dto.ProfileDto;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    void deleteProjectMember(String projectId, String memberId);
    List<ProfileDto> getPossibleProjectMembers(String id);
    Project addProjectMember(String id, ProjectMember member);
}
