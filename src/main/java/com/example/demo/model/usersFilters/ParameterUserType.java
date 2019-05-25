package com.example.demo.model.usersFilters;

import java.util.List;

public enum ParameterUserType {
    LOGIN("login", null),
    EMAIL("email", null);

    private String parameterName;
    private List<String> parameterValues;

    ParameterUserType(String parameterName, List<String> parameterValues) {
        this.parameterName = parameterName;
        this.parameterValues = parameterValues;
    }
}
