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

    @Null
    @DateTimeFormat
    private Date dueDate;

    @NotNull
    private Project project;

    @NotNull
    private IssueType issueType;

    @NotNull
    private IssuePriority issuePriority;

    @NotNull
    private IssueStatus issueStatus;

    @Null
    private Set<ChildIssue> childIssue = new HashSet<>();

    @Null
    private Set<Issue> issueRoles = new HashSet<>();

}
