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
    private String parentId;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "issueTypeId", referencedColumnName = "id")
    private IssueType issuetype;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name ="issuePriorityId", referencedColumnName = "id")
    private IssuePriority issuepriority;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "issueStatusId", referencedColumnName = "id")
    private IssueStatus issuestatus;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "issuerole_profile_issue",
            joinColumns = @JoinColumn(name = "issueid"),
            inverseJoinColumns = @JoinColumn(name = "profileid")
    )
    private Set<Profile> profiles = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "issuerole_profile_issue",
            joinColumns = @JoinColumn(name = "issueid"),
            inverseJoinColumns = @JoinColumn(name = "issueroleid")
    )
    private Set<IssueRole> issueroles = new HashSet<>();

    public Issue(String issueName, String issueDescription, Date startDate, Date dueDate, Project project, IssueType issuetype, IssuePriority issuepriority, IssueStatus issuestatus) {
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.project = project;
        this.issuetype = issuetype;
        this.issuepriority = issuepriority;
        this.issuestatus = issuestatus;
    }
}
