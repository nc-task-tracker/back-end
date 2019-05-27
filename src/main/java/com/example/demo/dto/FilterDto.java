package com.example.demo.dto;

import com.example.demo.model.Parameter;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String id;

    @JsonProperty("name")
    private String filterName;

    private Set<ParameterDto> parameters = new HashSet<>();
}
