package com.example.demo.controller;


import com.example.demo.config.Constants;
//import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.UserTokenModel;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import com.example.demo.dto.UserDto;
import com.example.demo.model.usersFilters.ParameterUser;
import com.example.demo.model.usersFilters.ParameterUserType;
import com.example.demo.model.usersFilters.UserFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService, ModelMapper modelMapper) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/login")
    public UserTokenModel login(@RequestBody UserDto loginUser) {
        return authenticationService.login(loginUser);
    }

    @PostMapping(value = "/register")
    public UserTokenModel register(@RequestBody @Valid UserDto registerUser) {
        return authenticationService.register(registerUser);
    }

    @GetMapping(value = "/register/search")
    public List<UserDto> searchUsers(@RequestParam(name = "login", required = false) String login,
                                     @RequestParam(name = "email", required = false) String email) {
        List<UserDto> result = new ArrayList<>();
        UserFilter userFilter = new UserFilter();
        if (login != null) {
            userFilter.getParameters().add(new ParameterUser(login, ParameterUserType.LOGIN));
        }
        if (email != null) {
            userFilter.getParameters().add(new ParameterUser(email, ParameterUserType.EMAIL));
        }
        this.userService.searchUsers(userFilter).forEach(
                user -> result.add(modelMapper.map(user, UserDto.class)));
        return result;
    }
}
