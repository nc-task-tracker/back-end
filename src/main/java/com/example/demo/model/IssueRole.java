package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "issuerole", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class IssueRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String issueRoleName;

    public IssueRole(String issueRoleName) {
        this.issueRoleName = issueRoleName;
    }
}
