package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public enum  IssueType {
    SUB_TASK,TASK,BUG,EPIC,IMPROVEMENT,NEW_FEATURE,STORY
}
