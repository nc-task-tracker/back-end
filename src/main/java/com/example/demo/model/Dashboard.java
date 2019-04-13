package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dashboard", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class Dashboard {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String dashboardName;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "dashboard_filter",
            joinColumns = @JoinColumn(name = "dashboardid"),
            inverseJoinColumns = @JoinColumn(name = "filterid")
    )
    private Set<Filter> filters = new HashSet<>();

    public Dashboard(String dashboardName, Set<Filter> filters) {
        this.dashboardName = dashboardName;
        this.filters = filters;
    }
}
