package com.example.demo.service;

import com.example.demo.model.Profile;
import com.example.demo.model.Project;

import java.util.List;

public interface ProfileService {
    Profile getProfileById(String id);
    Profile updateProfile(Profile profile);
    List<Profile> getAllProfiles();
    void deleteProfile(String id);
//    List<Project> getAllProjectByUserId(String id);
}
