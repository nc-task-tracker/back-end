package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAssigneeList(String inputValue);

}
