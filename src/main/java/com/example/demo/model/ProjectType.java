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
@Table(name = "projecttype", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class ProjectType {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String ptName;

    @OneToMany(mappedBy = "projecttype")
    private Set<Project> project = new HashSet<>();

    public ProjectType(String ptName, Set<Project> project) {
        this.ptName = ptName;
        this.project = project;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProjectType that = (ProjectType) object;
        return Objects.equals(id, that.id) &&
                Objects.equals(ptName, that.ptName) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ptName, project);
    }
}
