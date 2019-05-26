package com.example.demo.model.projectFilter;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
public class ParameterProject {
    private String parameterValue;
    @Enumerated(EnumType.STRING)
    private ParameterProjectType parameterProjectType;

    public ParameterProject(String parameterValue, ParameterProjectType parameterProjectType) {
        this.parameterValue = parameterValue;
        this.parameterProjectType = parameterProjectType;
    }
}
