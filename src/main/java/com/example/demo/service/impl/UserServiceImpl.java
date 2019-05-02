package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existByLogin(String username) {
        return userRepository.existsByLogin(username);
    }

    @Override
    public Boolean existByEmail(String email) {
        //return userRepository.existsByEmail(email);
        return null;
    }

    @Override
    public User addUser(User user) {
        User temp = userRepository.findByLogin(user.getLogin());

        if (user.getId() != null || temp == null) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));

            Set role = new HashSet<Role>(1);
            role.add(roleService.getRoleById("2"));
            user.setRoles(role);

            return userRepository.save(user);
        } else return null;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }
}
