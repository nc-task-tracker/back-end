package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.*;
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
    private User projectOwner;

    @NotNull
    private String projectCode;

    @Null
    private ProjectStatus projectStatus;

    @Null
    private Set<User> assigners = new HashSet<>();

    @Null
    private Set<Dashboard> dashboards = new HashSet<>();

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectOwner=" + projectOwner +
                ", projectCode='" + projectCode + '\'' +
                ", projectStatus=" + projectStatus +
                ", assigners=" + assigners +
                ", dashboards=" + dashboards +
                '}';
    }
}
