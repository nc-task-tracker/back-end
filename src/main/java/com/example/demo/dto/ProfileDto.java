package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    @Null
    private String id;
    @NotNull
    private String fullName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String skype;
    @NotNull
    private String telephone;
    @NotNull
    private String additional;
    @NotNull
    @DateTimeFormat
    private Date birthday;
    @NotNull
    private String description;

    private UserDto user;

    private Set<FilterDto> filters;
}
