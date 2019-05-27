package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public enum ParameterType {
    ISSUE_NAME("issue name", null),
    ISSUE_TYPE("issue type", Arrays.asList("BUG","TASK","EPIC", "IMPROVEMENT", "NEW FEATURE", "STORY", "SUB-TASK")),
    ISSUE_STATUS("issue status", Arrays.asList("OPEN", "CLOSED")),
    ISSUE_PRIORITY("issue priority", Arrays.asList("MAJOR", "CRITICAL", "BLOCKER", "TRIVIAL", "MINOR")),
    REPORTER("reporter", null),
    ASSIGNEE("assignee", null),
    PROJECT_NAME("project name", null);

    private String parameterName;
    private List<String> parameterValues;

    ParameterType(String parameterName, List<String> parameterValues) {
        this.parameterName = parameterName;
        this.parameterValues = parameterValues;
    }
}