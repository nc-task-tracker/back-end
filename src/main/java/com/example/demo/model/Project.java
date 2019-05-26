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

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User owner;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "dashboard_project",
            joinColumns = @JoinColumn(name = "projectid"),
            inverseJoinColumns = @JoinColumn(name = "dashboardid")
    )
    private Set<Dashboard> dashboards = new HashSet<>();

    public Project(String projectName, String projectDescription,
                   ProjectStatus projectStatus, String projectCode) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
        this.projectCode = projectCode;
    }
}
