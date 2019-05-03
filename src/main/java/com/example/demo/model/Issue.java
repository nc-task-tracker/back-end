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

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "childIssueId", referencedColumnName = "id")
    private Set<ChildIssue> childissue = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "issuerole_profile_issue",
            joinColumns = @JoinColumn(name = "profileid"),
            inverseJoinColumns = @JoinColumn(name = "issueroleid")
    )
    private Set<Issue> issueRoles = new HashSet<>();

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

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", issueName='" + issueName + '\'' +
                ", issueDescription='" + issueDescription + '\'' +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", issueType=" + issueType +
                ", issuePriority=" + issuePriority +
                ", issueStatus=" + issueStatus +
                ", project=" + project +
                ", childissue=" + childissue +
                ", issueRoles=" + issueRoles +
                '}';
    }
}
