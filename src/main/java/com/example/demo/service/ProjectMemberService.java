package com.example.demo.service;

import com.example.demo.dto.ProfileDto;

import java.util.List;

public interface ProjectMemberService {
    void deleteProjectMember(String projectId, String memberId);
    List<ProfileDto> getPossibleProjectMembers(String id);
}
