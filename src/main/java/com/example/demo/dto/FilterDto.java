package com.example.demo.dto;

import com.example.demo.model.Parameter;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class FilterDto {
    @Null
    private String id;

    @Null
    private String filterName;

    @NotNull
    private Set<Parameter> parametervalues = new HashSet<>();
}
