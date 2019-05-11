package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {
    @Null
    private String id;

    @NotNull
    private String issueName;

    @NotNull
    private String issueDescription;

    @Null
    @DateTimeFormat
    private Date startDate;

    //TODO: Change annotation on notnull
    @Null
    @DateTimeFormat
    private Date dueDate;


    //TODO: CHANGE ANNOTATION
//    @Null
//    private Project project;

    @Null
    private IssueType issuetype;

    @Null
    private IssuePriority issuepriority;

    @Null
    private IssueStatus issuestatus;
/*

    @Null
    private Set<ChildIssue> childissue = new HashSet<>();

    @Null
    private Set<Issue> issues = new HashSet<>();

    @Null
    private Set<Issue> issueRoles = new HashSet<>();

*/
/*
    @Null
    private User assignee;

    @Null
    private User reporter;
*/


}
