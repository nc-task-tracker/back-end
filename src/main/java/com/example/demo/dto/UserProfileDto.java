package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserProfileDto {

    private String id;

    @NotNull
    private String login;

    @NotNull
    private String firstName;
}
