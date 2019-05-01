package com.example.demo.config;

import com.example.demo.dto.UserDto;
import com.example.demo.model.AuthToken;
import lombok.Data;

@Data
public class UserTokenModel {
    UserDto user;
    AuthToken token;

    public UserTokenModel(UserDto user, AuthToken token) {
        this.user = user;
        this.token = token;
    }
}
