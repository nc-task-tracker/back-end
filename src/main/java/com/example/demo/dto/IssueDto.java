package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private ProjectDto project;

    @NotNull
    private IssueType issueType;

    @NotNull
    private IssuePriority issuePriority;

    @Null
    private IssueStatus issueStatus;

    @Null
    private String parentId;

    @NotNull
    private UserProfileDto reporter;
    private UserProfileDto assignee;
    private Set<IssueDto> subtasks = new HashSet<> ();

    private Set<CommentDto> comments = new HashSet<>();
}
