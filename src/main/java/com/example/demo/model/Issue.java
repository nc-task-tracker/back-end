package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

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
    private String parentId;

    @OneToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "reporterId", referencedColumnName = "id")
    private Profile reporter;

    @OneToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "assigneeId", referencedColumnName = "id")
    private Profile assignee;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private IssueType issuetype;

    @Enumerated(EnumType.STRING)
    private IssuePriority issuepriority;

    @Enumerated(EnumType.STRING)
    private IssueStatus issuestatus;

    public Issue(String issueName, String issueDescription, Date startDate, Date dueDate, String parentId, Profile reporter, Profile assignee, Project project, IssueType issuetype, IssuePriority issuepriority, IssueStatus issuestatus) {
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.parentId = parentId;
        this.reporter = reporter;
        this.assignee = assignee;
        this.project = project;
        this.issuetype = issuetype;
        this.issuepriority = issuepriority;
        this.issuestatus = issuestatus;
    }
}