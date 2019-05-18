package com.example.demo.model.projectFilter;

import lombok.Data;

@Data
public class ParameterProject {
    private String parameterValue;
    private ParameterProjectType parameterProjectType;

    public ParameterProject(String parameterValue, ParameterProjectType parameterProjectType) {
        this.parameterValue = parameterValue;
        this.parameterProjectType = parameterProjectType;
    }
}
