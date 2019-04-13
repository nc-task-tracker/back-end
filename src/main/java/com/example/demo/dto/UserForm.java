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
public class UserForm {
    @Null
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String firstPassword;

    @NonNull
    private String secondPassword;
}
