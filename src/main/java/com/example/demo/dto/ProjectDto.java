package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    @Null
    private String id;

    @NotBlank(message = "Project name shouldn't be blank")
    private String projectName;

    @NotBlank(message = "Project code shouldn't be blank")
    @Size(min = 3, max = 5, message = "Code size should be between 3 & 5")
    @Pattern(regexp = "[A-Z]+")
    private String projectCode;

    private String projectDescription;

    private UserDto projectOwner;
    @Null
    private ProjectStatus projectStatus;
    @Null
    private Set<DashboardDto> dashboards;
    @Null
    private Set<ProjectMemberDto> members;

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectOwner=" + projectOwner +
                ", projectStatus=" + projectStatus +
                ", dashboards=" + dashboards +
                ", members=" + members +
                '}';
    }
}
