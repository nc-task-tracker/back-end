package com.example.demo.model.projectFilter;

import java.util.List;

public enum ParameterProjectType {
    PROJECT_CODE("project code", null),
    PROJECT_NAME("project name", null);

    private String parameterName;
    private List<String> parameterValues;

    ParameterProjectType(String parameterName, List<String> parameterValues) {
        this.parameterName = parameterName;
        this.parameterValues = parameterValues;
    }
}
