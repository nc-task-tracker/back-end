package com.example.demo.service;

import com.example.demo.model.Profile;

import java.util.List;

public interface ProfileService {
    Profile saveProfile(Profile profile);
    Profile getProfileById(String id);
    Profile updateProfile(Profile profile);
    List<Profile> getAllProfiles();
    void deleteProfile(String id);
}
