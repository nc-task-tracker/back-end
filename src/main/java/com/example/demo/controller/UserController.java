package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping(value = "/{id}")
    public UserDto getUserById(@PathVariable(name = "id") String id) {
        return modelMapper.map(service.getUserById(id), UserDto.class);
    }

    @GetMapping(value = "/all")
    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = service.getAllUsers();
        for(User item : users) {
            usersDto.add(modelMapper.map(item, UserDto.class));
        }
        return usersDto;
    }

    @PostMapping
    public User saveUser(@RequestBody User account) {
        return service.saveUser(account);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto accountForUpdate) {
        User user = modelMapper.map(service.getUserById(accountForUpdate.getId()), User.class);
        return modelMapper.map(service.updateUser(user), UserDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") String id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
