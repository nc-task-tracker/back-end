package com.example.demo.model;

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
@Getter
@Setter
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
    private IssueType issueType;
    private IssuePriority issuePriority;
    private IssueStatus issueStatus;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "reporterId", referencedColumnName = "id")
    private Profile reporter;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "assignerID", referencedColumnName = "id")
    private Profile assigners;

    @OneToMany(mappedBy = "profile")
    private Set<Comment> comments = new HashSet<>();

    public Issue(String issueName, String issueDescription, Date startDate, Date dueDate, Project project, IssueType issuetype, IssuePriority issuepriority, IssueStatus issuestatus) {
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.project = project;
        this.issueType = issuetype;
        this.issuePriority = issuepriority;
        this.issueStatus = issuestatus;
    }


}
