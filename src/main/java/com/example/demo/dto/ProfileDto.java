package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
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

    @Null
    private Set<Dashboard> dashboards = new HashSet<>();
    @Null
    private Set<Filter> filters = new HashSet<>();
    @Null
    private Set<Project> projects = new HashSet<>();
}
