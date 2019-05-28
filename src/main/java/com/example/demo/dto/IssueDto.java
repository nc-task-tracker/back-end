package com.example.demo.dto;

import com.example.demo.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IssueDto {

    private String id;

    @NotBlank(message = "Name may not be empty!")
    @Size(min = 3, message = "Name may not be longer!")
    private String issueName;

    @NotBlank(message = "Description may not be empty!")
    @Size(min = 1, max = 1000)
    private String issueDescription;

    @NotNull
    @DateTimeFormat
    private Date startDate;

    @NotNull
    @DateTimeFormat
    private Date dueDate;

    @Null
    @NotNull
    private String parentId;


    private Project project;

    @NotNull
    private IssueType issueType;

    @NotNull
    private IssuePriority issuePriority;

    @Null
    private IssueStatus issueStatus;

    @Null
    private UserProfileDto assignee;

    private UserProfileDto reporter;

    private Set<Comment> comments = new HashSet<>();
}
