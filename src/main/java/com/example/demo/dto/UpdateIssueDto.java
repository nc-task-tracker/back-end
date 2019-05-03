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
public class UpdateIssueDto {
    @NotNull
    private String id;

    @NotNull
    @DateTimeFormat
    private Date dueDate;

    @NotNull
    private IssueType issuetype;

    @NotNull
    private IssuePriority issuepriority;

    @NotNull
    private IssueStatus issuestatus;
}
