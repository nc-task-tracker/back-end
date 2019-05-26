package com.example.demo.dto;

import com.example.demo.model.ParameterType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDto {
    private String id;

    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    private ParameterType parameterType;

    private String parameterValue;

//    @JsonProperty("value")
    @JsonProperty("listValue")
    private List<String> parameterValues;
}
