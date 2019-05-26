package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserProfileDto;
import com.example.demo.model.User;
import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    private final UserProfileService userProfileService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserProfileService userProfileService, ModelMapper modelMapper, UserService userService) {
        this.userProfileService = userProfileService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable(name = "id") String id) {
        return modelMapper.map(userService.getUserById(id), UserDto.class);
    }

    @GetMapping(value = "/all")
    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        for(User item : users) {
            usersDto.add(modelMapper.map(item, UserDto.class));
        }
        return usersDto;
    }

    @GetMapping(value="/assignee")
    public List<UserProfile> getAssignee(@RequestParam(required = false) String name) {
        return userProfileService.getAssigneeList(name);

    }

    @PostMapping
    public User saveUser(@RequestBody User account) {
        return userService.addUser(account);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto accountForUpdate) {
        User user = modelMapper.map(userService.getUserById(accountForUpdate.getId()), User.class);
        return modelMapper.map(userService.updateUser(user), UserDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
