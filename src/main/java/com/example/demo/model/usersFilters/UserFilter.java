package com.example.demo.model.usersFilters;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserFilter {
    private Set<ParameterUser> parameters = new HashSet<>();

    public UserFilter(Set<ParameterUser> parameters) {
        this.parameters = parameters;
    }

    public UserFilter() {

    }
}
