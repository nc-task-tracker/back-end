package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projectstatus", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class ProjectStatus {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String psName;

    @OneToMany(mappedBy = "projectstatus", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Project> project = new HashSet<>();

    public ProjectStatus(String psName, Set<Project> project) {
        this.psName = psName;
        this.project = project;
    }
}
