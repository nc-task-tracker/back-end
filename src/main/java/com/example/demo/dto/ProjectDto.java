package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    @Null
    private String id;

    @NotNull
    private String projectName;

    @NotNull
    private String projectDescription;

    @Null
    private ProjectType projecttype;

    @Null
    private ProjectStatus projectstatus;

    @Null
    private Set<Dashboard> dashboards = new HashSet<>();
}
