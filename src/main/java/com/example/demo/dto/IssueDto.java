
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

    //TODO: Change annotation on notnull

    @DateTimeFormat
    private Date dueDate;

    private String parentId;
//
//    @Null
//    private String project;

    @NotNull
    private IssueType issueType;

    @NotNull
    private IssuePriority issuePriority;

    @Null
    private IssueStatus issueStatus;


    private String assignee;

    private String reporter;
}
