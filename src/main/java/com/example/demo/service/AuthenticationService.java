package com.example.demo.service;

import com.example.demo.config.UserTokenModel;
import com.example.demo.dto.UserDto;

public interface AuthenticationService {
        UserTokenModel login(UserDto userDto);
        UserTokenModel register(UserDto userDto);
}
