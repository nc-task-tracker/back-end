package com.example.demo.controller;

import com.example.demo.dto.DashboardDto;
import com.example.demo.dto.FilterDto;
import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.model.Dashboard;
import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import com.example.demo.service.DashboardService;
import com.example.demo.service.FilterService;
import com.example.demo.service.ProfileService;
import com.example.demo.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {

    private ProfileService service;
    private ModelMapper modelMapper;
    private DashboardService dashboardService;
    private FilterService filterService;
    private ProjectService projectService;

    @Autowired
    public ProfileController(ProfileService service,
                             ModelMapper modelMapper,
                             DashboardService dashboardService) {
        this.dashboardService = dashboardService;
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ProfileDto getProfileById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getProfileById(id), ProfileDto.class);
    }

    @GetMapping(value = "/all")
    public List<ProfileDto> getAllProfiles() {
        List<ProfileDto> profilesDto = new ArrayList<>();
        List<Profile> users = service.getAllProfiles();
        for(Profile item : users) {
            profilesDto.add(modelMapper.map(item, ProfileDto.class));
        }
        return profilesDto;
    }

    @PutMapping(value = "/change-profile")
    public ProfileDto updateProfile(@RequestBody ProfileDto profileForUpdate) {
        Profile profile = modelMapper.map(profileForUpdate, Profile.class);
        Profile update = service.updateProfile(profile);
        ProfileDto profileDto = modelMapper.map(update, ProfileDto.class);
        return (ProfileDto) profileDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProfile(@PathVariable(name = "id") String id) {
        service.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "/{user_id}/all_dashboards")
    public List<DashboardDto> getAllDashboardById(@PathVariable(name = "user_id") String id) {
        List<DashboardDto> dashboardsDto = new ArrayList<>();
        for(Dashboard item : dashboardService.getAllDashboardByUserId(id)) {
            dashboardsDto.add(modelMapper.map(item, DashboardDto.class));
        }
        return dashboardsDto;
    }
    /*@GetMapping(value = "/{user_id}/all_projects")
    public List<ProjectDto> getAllProjectById(@PathVariable(name = "user_id") String id) {
        List<ProjectDto> projectsDto = new ArrayList<>();
        for(Project item : projectService.getAllProjectByUserId(id) ){
            projectsDto.add(modelMapper.map(item, ProjectDto.class));
        }
    return null;
    }
    @GetMapping(value = "/{user_id}/all_filters")
    public List<FilterDto> getAllFilterById(@PathVariable(name = "user_id") String id) {
        List<FilterDto> filtersDto = new ArrayList<>();
        for (Project item : filterService.getAllDashboardByUserId(id)) {
            filtersDto.add(modelMapper.map(item, FilterDto.class));
        }
        return null;
    }*/

}
