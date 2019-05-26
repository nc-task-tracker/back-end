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
    @Null
    private ProjectDto project;

    @NotNull
    private IssueType type;

    @NotNull
    private IssuePriority priority;

    @Null
    private IssueStatus issueStatus;

    @Null
    private String parentId;

    @NotNull
    private ProfileDto reporter;

    private UserProfileDto assignee;

    private Set<IssueDto> subtasks = new HashSet<> ();

    private Set<CommentDto> comments = new HashSet<>();
}
