package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Null
    @NotNull
    private String projectId;
    private Project project;

    @NotNull
    private IssueType issueType;

    @NotNull
    private IssuePriority issuePriority;

    @Null
    private IssueStatus issueStatus;

    @Null
    private String assignee;

    @Null
    private String reporter;

    @Null
    private Set<ChildIssue> childIssue = new HashSet<>();

    private Set<Comment> comments = new HashSet<>();
}
