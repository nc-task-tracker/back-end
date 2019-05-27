package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    private String projectCode;
    private String projectDescription;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @ManyToOne(fetch= FetchType.EAGER, cascade = {
            CascadeType.MERGE
    })
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @ManyToMany(fetch= FetchType.EAGER, cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "members_id")
    )
    private Set<ProjectMember> members = new HashSet<>();

    @ManyToMany(fetch= FetchType.EAGER, cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "dashboard_project",
            joinColumns = @JoinColumn(name = "projectid"),
            inverseJoinColumns = @JoinColumn(name = "dashboardid")
    )
    private Set<Dashboard> dashboards = new HashSet<>();


    public Project(String projectName, String projectCode, String projectDescription, ProjectStatus projectStatus, User owner, Set<ProjectMember> members) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
        this.owner = owner;
        this.members = members;
        this.dashboards = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectStatus=" + projectStatus +
                ", owner=" + owner +
                ", members=" + members +
                ", dashboards=" + dashboards +
                '}';
    }
}
