package com.example.demo.dto;

import com.example.demo.model.Filter;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    @Null
    private String id;

    @NotNull
    private String dashboardName;

    @NotNull
    private UserDto user;

    @Null
    private Set<Filter> filters = new HashSet<>();
}
