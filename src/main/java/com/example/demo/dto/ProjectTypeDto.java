package com.example.demo.dto;

import com.example.demo.model.Project;
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
public class ProjectTypeDto {
    @Null
    private String id;

    //    @NotNull
    private String ptName;

    @Null
    private Set<Project> project = new HashSet<>();
}
