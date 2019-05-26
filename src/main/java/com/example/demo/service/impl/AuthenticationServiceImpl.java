package com.example.demo.service.impl;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.UserTokenModel;
import com.example.demo.dto.UserDto;
import com.example.demo.model.AuthToken;
import com.example.demo.model.Filter;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    private ModelMapper modelMapper;

    @Autowired
    public AuthenticationServiceImpl(ModelMapper modelMapper,
                                     UserService userService,
                                     JwtTokenUtil jwtTokenUtil,
                                     AuthenticationManager authenticationManager,
                                     @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserTokenModel login(UserDto userDto) {
        try {
            User user = userService.getUserByUsername(userDto.getLogin());
            String token = getToken(userDto);
            return new UserTokenModel(modelMapper.map(user, UserDto.class), new AuthToken(token));
        } catch (AuthenticationException ex){
            return null;
        }
    }

    @Override
    public UserTokenModel register(UserDto userDto) {
        User user = userService.addUser(modelMapper.map(userDto, User.class));

        String token = getToken(userDto);

        return new UserTokenModel(modelMapper.map(user, UserDto.class), new AuthToken(token));
    }

    private String getToken(UserDto user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, user.getPassword(), userDetails.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return jwtTokenUtil.generateToken(authenticationToken);
    }
}
