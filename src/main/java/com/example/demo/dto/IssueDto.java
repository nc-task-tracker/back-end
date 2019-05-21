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

    private String projectId;

    @NotNull
    private IssueType issueType;

    @NotNull
    private IssuePriority issuePriority;

    @NotNull
    private IssueStatus issueStatus;

    private String reporterId;

    private String assignerId;

    private Set<ChildIssue> childIssue = new HashSet<>();

    private Set<Comment> comments = new HashSet<>();
}
