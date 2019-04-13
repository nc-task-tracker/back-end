package com.example.demo.dto;

import com.example.demo.model.Filter;
import com.example.demo.model.IssueRole;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private String firstName;

    @NotNull
    private String secondName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @DateTimeFormat
    private Date birthday;

    @Null
    private User user;

    @Null
    private Set<IssueRole> roles = new HashSet<>();

    @Null
    private Set<Filter> filters = new HashSet<>();
}
