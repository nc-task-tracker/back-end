package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserProfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public List<UserProfile> getAssigneeList(String inputValue) {
        Sort sort = new Sort(Sort.Direction.ASC, "login");
        Pageable pageable = PageRequest.of(1, 10, sort);
 /*       List<User> resultSearch = StringUtils.isEmpty(inputValue) ?
                userRepository.findAll(sort)
                : userRepository.findUserProfileBySubstring(String.format("%%%s%%", inputValue), sort);*/

/*        return resultSearch.stream()
                .map(item -> new UserProfile(item.getId(), item.getProfile() == null ? null : item.getProfile().getFirstName(), item.getLogin()))
                .collect(Collectors.toCollection(LinkedList::new));*/
        return null;
    }


}
