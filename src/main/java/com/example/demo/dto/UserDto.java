package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;

    @NonNull
    private String login;

    @Null
    private String email;

    @NonNull
    private String password;
}
