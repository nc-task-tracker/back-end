package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ProjectMemberDto;
import com.example.demo.dto.util.PageDto;
import com.example.demo.dto.util.TableSortParametersDTO;
import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import com.example.demo.model.ProjectMember;
import com.example.demo.repository.ProjectMemberRepository;
import com.example.demo.service.ProjectMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.Constants.HEADER_STRING;

@RestController
@RequestMapping("/api/project/members")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService service;

    @Autowired
    private ProjectMemberRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping(value = "/{id}")
    public List<ProjectMember> getAllProjectMembers(@PathVariable(name = "id") String id) {
        return repository.findAll();
    }

    @DeleteMapping(value = "/{projectId}/{memberId}")
    public ResponseEntity deleteProjectMember(@PathVariable("projectId") String projectId,
                                              @PathVariable("memberId") String memberId,
                                              @RequestHeader(HEADER_STRING) String token){
        service.deleteProjectMember(projectId,memberId,token);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/possible")
    public List<ProfileDto> getPossibleProjectMembers(@PathVariable("id") String id){
        return service.getPossibleProjectMembers(id);
    }

    @PostMapping(value = "/{id}/add")
    public ProjectMemberDto addProjectMember(@RequestBody ProjectMemberDto projectMemberDto,
                                    @PathVariable("id") String id,
                                    @RequestHeader(HEADER_STRING) String token){
        return modelMapper.map(service.addProjectMember(id,modelMapper.map(projectMemberDto,ProjectMember.class),
                token),ProjectMemberDto.class);
    }

    @PostMapping(value = "/project/{id}/sort")
    public PageDto<ProjectMemberDto> getPageData(@PathVariable("id") String id,
                                                 @RequestBody TableSortParametersDTO parametersDTO){
        return service.getTablePageData(id,parametersDTO);
    }
}
