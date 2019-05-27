package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "project_member",schema = "new_schema")
@NoArgsConstructor
@Getter
@Setter
public class ProjectMember {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;


    @Enumerated(EnumType.STRING)
    private ProjectRole role;

    public ProjectMember(Profile profile, ProjectRole role) {
        this.profile = profile;
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "profile=" + profile +
                ", role=" + role +
                '}';
    }
}
