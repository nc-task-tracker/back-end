package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "filter", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class Filter {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String filterName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "selectedvalue",
            joinColumns = @JoinColumn(name = "filterNameId"),
            inverseJoinColumns = @JoinColumn(name = "paramValueId")
    )
    private Set<ParameterValue> parametervalues = new HashSet<>();

    public Filter(String filterName, Set<ParameterValue> parametervalues) {
        this.filterName = filterName;
        this.parametervalues = parametervalues;
    }
}
