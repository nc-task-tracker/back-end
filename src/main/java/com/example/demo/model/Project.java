package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String projectName;
    private String projectDescription;
    private ProjectType projectType;
    private String projectOwner;
    private ProjectStatus projectStatus;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "dashboard_project",
            joinColumns = @JoinColumn(name = "projectid"),
            inverseJoinColumns = @JoinColumn(name = "dashboardid")
    )
    private Set<Dashboard> dashboards = new HashSet<>();

    public Project(String projectName, String projectDescription, ProjectType projectType, ProjectStatus projectStatus) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return Objects.equals(id, project.id) &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(projectDescription, project.projectDescription) &&
                //Objects.equals(projectType, project.projectType) &&
                Objects.equals(projectStatus, project.projectStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, projectDescription, /*projectType,*/ projectStatus);
    }
}
