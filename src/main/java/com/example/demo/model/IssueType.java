package com.example.demo.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

public enum IssueType {
    BUG,
    EPIC,
    IMPROVEMENT,
    NEW_FEATURE,
    STORY,
    SUB_TASK,
    TASK;
}
