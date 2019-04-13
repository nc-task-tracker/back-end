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

    public ProjectStatus(String psName) {
        this.psName = psName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProjectStatus that = (ProjectStatus) object;
        return Objects.equals(id, that.id) &&
                Objects.equals(psName, that.psName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, psName);
    }
}
