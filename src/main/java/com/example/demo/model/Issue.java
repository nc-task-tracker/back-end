package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issue", schema = "new_schema")
@Data
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String issueName;
    private String issueDescription;
    private Date startDate;
    private Date dueDate;
    private String issueCode;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private IssuePriority issuePriority;

    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "reporterID", referencedColumnName = "id")
    private Profile reporter;


    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "assignerID", referencedColumnName = "id")
    private Profile assignee;

    @OneToMany(mappedBy = "issue")
    private Set<Comment> comments = new HashSet<>();

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", issueName='" + issueName + '\'' +
                ", issueDescription='" + issueDescription + '\'' +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", issueCode='" + issueCode + '\'' +
                ", project=" + project +
                ", issueType=" + issueType +
                ", issuePriority=" + issuePriority +
                ", issueStatus=" + issueStatus +
                ", reporter=" + reporter +
                ", assignee=" + assignee +
                ", comments=" + comments +
                '}';
    }
}
