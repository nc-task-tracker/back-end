package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableSortParametersDTO {

    @NotNull
    private String columnName;

    @NotNull
    private String direction;

    @NotNull
    private int page;

    @NotNull
    private int maxElemOnPage;

    @Override
    public String toString() {
        return "TableSortParametersDTO{" +
                "columnName='" + columnName + '\'' +
                ", direction='" + direction + '\'' +
                ", page=" + page +
                ", maxElemOnPage=" + maxElemOnPage +
                '}';
    }
}
