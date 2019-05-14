package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfile {
    private String id;
    private String login;
    private String FIO;

    public UserProfile(String id, String login, String FIO) {
        this.id = id;
        this.login = login;
        this.FIO = FIO;
    }
}