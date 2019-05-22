package com.example.demo.controller;

import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.model.Profile;
import com.example.demo.model.ProjectMember;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.service.ProjectMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project/members")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public List<ProjectMemberDto> getAllProjectMembers(@PathVariable(name = "id") String id){
        return null;
    }

    @DeleteMapping(value = "/delete/{projectId}/{memberId}")
    public ResponseEntity deleteProjectMember(@PathVariable("projectId") String projectId,
                                              @PathVariable("memberId") String memberId){
        service.deleteProjectMember(projectId,memberId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/possible")
    public List<ProfileDto> getPossibleProjectMembers(@PathVariable("id") String id){
        return service.getPossibleProjectMembers(id);
    }
}
