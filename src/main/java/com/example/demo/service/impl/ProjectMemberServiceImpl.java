package com.example.demo.service.impl;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.IssueDto;
import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.*;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectMemberService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private ProjectMemberRepository memberRepository;
    private ProfileRepository profileRepository;
    private ModelMapper mapper;
    private ProjectRepository projectRepository;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @Autowired
    public ProjectMemberServiceImpl(ProjectMemberRepository memberRepository,
                                    ProfileRepository profileRepository,
                                    ProjectRepository projectRepository,
                                    JwtTokenUtil jwtTokenUtil,
                                    UserService userService,
                                    ModelMapper mapper) {
        this.memberRepository = memberRepository;
        this.profileRepository = profileRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void deleteProjectMember(String projectId, String memberId, String token) {
        Project project = projectRepository.findProjectById(projectId);
        String login = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        User user = userService.getUserByUsername(login);
        ProjectMember removedMember = memberRepository.findById(memberId).get();

        if (project != null && user != null) {
            Iterator<ProjectMember> iterator = project.getMembers().iterator();
            ProjectMember currentMember = null;

            while (iterator.hasNext()) {
                ProjectMember temp = iterator.next();
                if (temp.getProfile().getUser().getId().equals(user.getId()))
                    currentMember = temp;
            }

            if (currentMember != null && currentMember.getRole() != ProjectRole.DEVELOPER) {
                if (removedMember.getRole() == ProjectRole.OWNER)
                    return;

                project.getMembers().remove(removedMember);
                projectRepository.save(project);
                memberRepository.delete(removedMember);
            }
        }


    }

    @Override
    public List<ProfileDto> getPossibleProjectMembers(String id) {
        return profileRepository.getPossibleProjectMembers(id)
                .stream()
                .map(value ->
                        mapper.map(value, ProfileDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public ProjectMember addProjectMember(String id, ProjectMember member, String token) {
        Project project = projectRepository.findProjectById(id);
        String login = jwtTokenUtil.getUsernameFromToken(token.substring(7));

        if (project != null) {
            ProjectMember currentUser = null;
            Iterator<ProjectMember> iterator = project.getMembers().iterator();
            while (iterator.hasNext()) {
                ProjectMember temp = iterator.next();

                if (temp.getProfile().getUser().getLogin().equals(login))
                    currentUser = temp;
            }

            if (currentUser.getRole() != ProjectRole.DEVELOPER && currentUser != null) {
                project.getMembers().add(member);
                projectRepository.save(project);

                return member;
            }
        }
        return null;
    }

    @Override
    public PageDto<ProjectMemberDto> getTablePageData(String id, TableSortParametersDTO parametersDTO) {
        PageDto<ProjectMemberDto> pageDto = new PageDto<>();

        Sort sort = new Sort(parametersDTO.get_direction().equals("asc")? Sort.Direction.ASC:Sort.Direction.DESC,
                parametersDTO.get_columnName());
        Pageable pageable = PageRequest.of(parametersDTO.get_page(),parametersDTO.get_maxElemOnPage(),sort);

        Page<ProjectMember> all = memberRepository.findPageDataByProjectId(id,pageable);

        pageDto.setTotalElem(all.getTotalElements());
        pageDto.setTotalPages(all.getTotalPages());
        pageDto.setList(all.get().map(value -> mapper.map(value,ProjectMemberDto.class)).collect(Collectors.toList()));
        pageDto.setPageSize(all.getSize());

        return pageDto;
    }
}

