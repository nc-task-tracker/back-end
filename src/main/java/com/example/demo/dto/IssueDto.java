
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
    @NotNull
    @DateTimeFormat
    private Date dueDate;

    @Null
    private String parentId;

    @NotNull
    private String project;

    @NotNull
    private IssueType issuetype;

    @NotNull
    private IssuePriority issuepriority;

    @Null
    private IssueStatus issuestatus;

    private String assignee;

    private String reporter;
}
