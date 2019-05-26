package com.example.demo.service.impl;


import com.example.demo.model.QUser;
import com.example.demo.model.Profile;
import com.example.demo.model.Project;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.usersFilters.UserFilter;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.demo.model.usersFilters.ParameterUserType.EMAIL;
import static com.example.demo.model.usersFilters.ParameterUserType.LOGIN;

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

    public User saveUser(User user) {
        User temp = userRepository.findByLogin(user.getLogin());
        if (user.getId() != null || temp == null) {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));

            Set role = new HashSet<Role>(1);
            role.add(roleService.getRoleById("bb65ce3f-d8b9-4b08-8737-3cf55caf4bdd"));
            user.setRoles(role);

            return userRepository.save(user);
        } else return null;
    }

    @Override
    public User addUser(User user) {
        User temp = userRepository.findByLogin(user.getLogin());

        if (user.getId() != null || temp == null) {
            user.setPassword(this.encodePassword(user.getPassword()));

            Set role = new HashSet<Role>(1);
            role.add(roleService.getRoleById("0fc92c1b-5180-4036-8fe7-32f01b7d750d"));
            user.setRoles(role);

            return userRepository.save(user);
        } else return null;
    }

    @Override
    public String encodePassword(String password){
        return this.bcryptEncoder.encode(password);
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
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public List<User> searchUsers(UserFilter filter) {
        return (List<User>) this.userRepository.findAll(userSearchPredicate(filter));
    }

    public Predicate userSearchPredicate(UserFilter userFilter) {
        BooleanBuilder expression = new BooleanBuilder();
        QUser user = QUser.user;

        userFilter.getParameters().forEach(parameterUser -> {
            switch (parameterUser.getParameterUserType()) {
                case LOGIN:
                    expression.and(user.login.stringValue().in(parameterUser.getParameterValue()));
                    break;
                case EMAIL:
                    expression.and(user.email.stringValue().in(parameterUser.getParameterValue()));
                    break;
            }
        });
        return expression;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getNotProjectAssigners(String projectId) {
        return userRepository.getNotProjectAssigners(projectId);
    }

    public List<Project> getPossibleProjects(String username) {
        return (List<Project>) userRepository.findByLogin(username).getProfile();
    }
}
