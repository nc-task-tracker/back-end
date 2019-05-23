package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    Boolean existByLogin(String username);
    Boolean existByEmail(String email);
    User addUser(User user);
    User updateUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
    void deleteUser(String id);
    User getUserByUsername(String username);
    List<User> getNotProjectAssigners(String projectId);
    List<Project> getPossibleProjects(String username);
}
