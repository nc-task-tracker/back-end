package com.example.demo.controller;

import com.example.demo.dto.ProfileDto;
import com.example.demo.model.Profile;
import com.example.demo.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {
    private ProfileService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProfileController(ProfileService service) {
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
}
