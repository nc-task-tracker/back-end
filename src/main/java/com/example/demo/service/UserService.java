package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
    void deleteUser(String id);
    User getUserByUsername(String username);
}
