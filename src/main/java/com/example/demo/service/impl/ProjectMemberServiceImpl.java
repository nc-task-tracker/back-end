package com.example.demo.service.impl;

import com.example.demo.dto.ProfileDto;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.service.ProjectMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private ProjectMemberRepository repository;
    private ProfileRepository profileRepository;
    private ModelMapper mapper;

    @Autowired
    public ProjectMemberServiceImpl(ProjectMemberRepository memberRepository,
                                    ProfileRepository profileRepository,
                                    ModelMapper mapper) {
        this.repository = memberRepository;
        this.profileRepository = profileRepository;
        this.mapper = mapper;
    }

    @Override
    public void deleteProjectMember(String projectId, String memberId) {
        repository.deleteProjectMember(projectId, memberId);
    }

    @Override
    public List<ProfileDto> getPossibleProjectMembers(String id) {
        System.out.println(id);
        return profileRepository.getPossibleProjectMembers(id)
                .stream()
                .map(value -> {
                    return mapper.map(value, ProfileDto.class);
                }).collect(Collectors.toList());
    }
}
