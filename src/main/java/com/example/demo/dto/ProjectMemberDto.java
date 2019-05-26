package com.example.demo.dto;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public String toString() {
        return "ProjectMemberDto{" +
                "id='" + id + '\'' +
                ", profile=" + profile +
                ", role=" + role +
                '}';
    }
}
