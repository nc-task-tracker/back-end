package com.example.demo.dto;

import com.example.demo.model.ProjectRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProjectMemberDto {

    @NotNull
    private String id;

    @NotNull
    private ProfileDto profile;

    @NotNull
    private ProjectRole role;
}
