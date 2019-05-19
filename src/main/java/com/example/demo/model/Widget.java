package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "widget", schema = "new_schema")
@Data
public class Widget {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String widgetName;
    private Integer widgetOrder;
    private String type;
    private String description;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "filterId", referencedColumnName = "id")
    private Filter filter;

    private String Assign;
    private String Priority;
    private String dashboardId;
}
