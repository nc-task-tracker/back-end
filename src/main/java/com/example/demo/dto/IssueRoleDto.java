package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IssueRoleDto {
    @Null
    private String id;

    @NotNull
    private String issueRoleName;
}
