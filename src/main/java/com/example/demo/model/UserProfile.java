package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserProfile {
    private String id;
    private String login;
    private String FIO;

    public UserProfile(String id, String FIO, String login) {
        this.id = id;
        this.login = login;
        this.FIO = FIO;
    }
}
