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
    private String parentId;

    @OneToOne(cascade = {
            CascadeType.ALL
    })
    @JoinColumn(name = "reporterId", referencedColumnName = "id")
    private Profile reporter;

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
    @JoinColumn(name = "assignerID", referencedColumnName = "id")
    private Profile assigner;

    @OneToMany(mappedBy = "issue")
    private Set<Comment> comments = new HashSet<> ();
}