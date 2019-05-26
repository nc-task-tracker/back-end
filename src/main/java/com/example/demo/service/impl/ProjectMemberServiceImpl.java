package com.example.demo.service.impl;

import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private ProjectMemberRepository memberRepository;
    private ProfileRepository profileRepository;
    private ModelMapper mapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectMemberServiceImpl(ProjectMemberRepository memberRepository,
                                    ProfileRepository profileRepository,
                                    ModelMapper mapper) {
        this.memberRepository = memberRepository;
        this.profileRepository = profileRepository;
        this.mapper = mapper;
    }

    @Override
    public void deleteProjectMember(String projectId, String memberId) {
        Project project = projectRepository.findProjectById(projectId);
        Optional<ProjectMember> member = memberRepository.findById(memberId);

        if(member.isPresent()){
            project.getMembers().remove(member.get());
            System.out.println(project);
            projectRepository.save(project);
        }
    }

    @Override
    public List<ProfileDto> getPossibleProjectMembers(String id) {
        System.out.println(id);
        return profileRepository.getPossibleProjectMembers(id)
                .stream()
                .map(value ->
                     mapper.map(value, ProfileDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public Project addProjectMember(String id, ProjectMember member) {
        Project project = projectRepository.findProjectById(id);
        project.getMembers().add(member);
        System.out.println(project.toString());
        projectRepository.save(project);
        //memberRepository.save(member.setProject(project));

        return project;
    }
}

