package com.example.demo.dto;

import com.example.demo.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Null
    private String id;

    @NotNull
    private String login;

    @Null
    private String email;

    @NotNull
    private String password;
}
