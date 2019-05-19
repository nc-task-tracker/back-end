package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    Boolean existByLogin(String username);
    Boolean existByEmail(String email);
    User addUser(User user);
    User updateUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
    void deleteUser(String id);
    User getUserByUsername(String username);
}
