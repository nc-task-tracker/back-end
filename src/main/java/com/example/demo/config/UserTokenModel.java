package com.example.demo.config;

import com.example.demo.model.AuthToken;
import com.example.demo.model.User;
import lombok.Data;

@Data
public class UserTokenModel {
    User user;
    AuthToken token;

    public UserTokenModel(User user, AuthToken token) {
        this.user = user;
        this.token = token;
    }
}
