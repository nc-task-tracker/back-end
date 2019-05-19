package com.example.demo.dto;

import com.example.demo.model.Filter;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Null;

@Data
public class WidgetDto {
    @Null
    private String id;

    @Null
    private String widgetName;

    @Null
    private Integer widgetOrder;

    @Null
    private String type;

    @Null
    private Filter filter;

    @Null
    private String assign;

    @Null
    private String Priority;

    @Null
    private String dashboardId;

}
