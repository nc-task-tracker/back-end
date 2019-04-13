package com.example.demo.service.impl;

import com.example.demo.model.Profile;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository repository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Profile saveProfile(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public Profile getProfileById(String id) {
        return repository.findProfileById(id);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        return repository.save(profile);
    }

    @Override
    public List<Profile> getAllProfiles() {
        return (List<Profile>) repository.findAll();
    }

    @Override
    public void deleteProfile(String id) {
        repository.deleteById(id);
    }
}
