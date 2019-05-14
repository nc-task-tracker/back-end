package com.example.demo.dto;

import com.example.demo.model.Filter;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String firstName;

    private String secondName;

    @Email
    private String email;

    @DateTimeFormat
    private Date birthday;

    private User user;

    private Set<Filter> filters = new HashSet<>();
}
