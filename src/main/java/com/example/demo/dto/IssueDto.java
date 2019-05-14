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
    private String id;

    private String issueName;

    private String issueDescription;

    @DateTimeFormat
    private Date startDate;

    @DateTimeFormat
    private Date dueDate;

    private String parentId;

    private Project project;

    private IssueType issuetype;

    private IssuePriority issuepriority;

    private IssueStatus issuestatus;

    private Profile assignee;

    private Profile reporter;
}
