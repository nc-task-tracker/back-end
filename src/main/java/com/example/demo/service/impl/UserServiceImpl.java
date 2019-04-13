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
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUser(User user) {
        User temp = repository.findByLogin(user.getLogin());
        if (user.getId() != null || temp == null) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));

            Set role = new HashSet<Role>(1);
            role.add(roleService.getRoleById("bb65ce3f-d8b9-4b08-8737-3cf55caf4bdd"));
            user.setRoles(role);

            return repository.save(user);
        } else return null;
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return repository.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)repository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.findByLogin(username);
    }

    @Override
    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}
