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

    @Column(name="dashboard_name")
    private String name;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "dashboardId", referencedColumnName = "id")
    private Set<Widget> widgets = new HashSet<>();

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    public Dashboard(String name, Set<Widget> widgets) {
        this.name = name;
        this.widgets = widgets;
    }
}
