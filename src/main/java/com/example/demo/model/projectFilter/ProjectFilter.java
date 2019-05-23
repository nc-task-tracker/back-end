package com.example.demo.model.projectFilter;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProjectFilter {
    private Set<ParameterProject> parameters = new HashSet<>();

    public ProjectFilter(Set<ParameterProject> parameters) {
        this.parameters = parameters;
    }

    public ProjectFilter() {

    }
}
