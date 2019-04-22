package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public enum  IssueStatus {
    Open,Resolved,Reopened,InProgress,Closed
}
