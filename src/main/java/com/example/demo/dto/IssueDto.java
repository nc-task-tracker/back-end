package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.*;
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
@ToString
public class IssueDto {
    @Null
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @DateTimeFormat
    private Date startDate;

    @NotNull
    @DateTimeFormat
    private Date dueDate;

    @NotNull
    private String projectId;
    private Project project;

    @NotNull
    private IssueType type;

    @NotNull
    private IssuePriority priority;

    @NotNull
    private IssueStatus status;

    @Null
    private Profile reporter;

    @Null
    private Profile assignee;

    private Set<ChildIssue> childIssue = new HashSet<>();

    private Set<Comment> comments = new HashSet<>();
}
