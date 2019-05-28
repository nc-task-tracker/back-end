package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {
    private String id;

    @Size(min = 3, max = 15, message = "Name may not be longer!")
    @JsonProperty("name")
    private String filterName;

    @NotBlank(message = "Parameters may not empty!")
    @Size(min = 1)
    private Set<ParameterDto> parameters = new HashSet<>();
}
