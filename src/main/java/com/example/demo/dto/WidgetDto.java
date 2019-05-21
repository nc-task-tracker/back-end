package com.example.demo.dto;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Null;

@Data
public class WidgetDto {
    @Null
    private String id;

    @NonNull
    private String login;

    @Null
    private String email;

    @NonNull
    private String password;
}
