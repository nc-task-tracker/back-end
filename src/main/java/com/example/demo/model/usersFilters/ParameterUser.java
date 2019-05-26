package com.example.demo.model.usersFilters;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ParameterUser {
    private String parameterValue;
    @Enumerated(EnumType.STRING)
    private ParameterUserType parameterUserType;

    public ParameterUser(String parameterValue, ParameterUserType parameterUserType) {
        this.parameterValue = parameterValue;
        this.parameterUserType = parameterUserType;
    }
}
