package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Getter
@Setter
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
    private String code;
    private String parentId;

    @OneToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "reporterId", referencedColumnName = "id")
    @JsonManagedReference
    private Profile reporter;

    @OneToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "assigneeId", referencedColumnName = "id")
    @JsonManagedReference
    private Profile assignee;

    @ManyToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Enumerated(EnumType.STRING)
    private IssuePriority issuePriority;
    @OneToMany(mappedBy = "issue")
    private Set<Comment> comments = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus;

    public Issue(String name, String description, Date startDate, Date dueDate, String parentId, Profile reporter, Profile assignee, Project project, IssueType issueType, IssuePriority issuePriority, IssueStatus issueStatus) {
        this.issueName = name;
        this.issueDescription = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.parentId = parentId;
        this.reporter = reporter;
        this.assignee = assignee;
        this.project = project;
        this.issueType = issueType;
        this.issuePriority = issuePriority;
        this.issueStatus = issueStatus;
    }
}
