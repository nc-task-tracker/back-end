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

    @NotNull
    @DateTimeFormat
    private Date startDate;

    @NotNull
    @DateTimeFormat
    private Date dueDate;

    @NotNull
    private Project project;

    @NotNull
    private IssueType issuetype;

    @NotNull
    private IssuePriority issuepriority;

    @NotNull
    private IssueStatus issuestatus;

    @NotNull
    private Set<ChildIssue> childissue = new HashSet<>();

    @NotNull
    private Set<Issue> issues = new HashSet<>();

    @NotNull
    private Set<Issue> issueRoles = new HashSet<>();

}
