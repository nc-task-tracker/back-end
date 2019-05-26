package com.example.demo.controller;


import com.example.demo.config.Constants;
//import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.UserTokenModel;
import com.example.demo.dto.UserDto;
import com.example.demo.model.AuthToken;
import com.example.demo.model.LoginUser;
import com.example.demo.model.usersFilters.ParameterUser;
import com.example.demo.model.usersFilters.ParameterUserType;
import com.example.demo.model.usersFilters.UserFilter;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/authentication/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;

    private final UserService userService;

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;


    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, UserService userService, @Qualifier("userDetailsService") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.modelMapper = modelMapper;

    }

    @PostMapping
    public UserTokenModel register(@RequestBody LoginUser loginUser) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getLogin());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, loginUser.getPassword(), userDetails.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        final String token = jwtTokenUtil.generateToken(authenticationToken);


        return new UserTokenModel(modelMapper.map(userService.getUserByUsername(loginUser.getLogin()), UserDto.class), new AuthToken(token));
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

    @GetMapping(value = "/expDate")
    public Date GetExpDate(@PathVariable String token) {
        token = token.replace(Constants.TOKEN_PREFIX, "");
        return jwtTokenUtil.getExpirationDateFromToken(token);
    }

}
