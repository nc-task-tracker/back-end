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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Parameter> parameters = new HashSet<>();

    public Filter(String filterName, Set<Parameter> parameter) {
        this.filterName = filterName;
        this.parameters = parameter;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "id='" + id + '\'' +
                ", filterName='" + filterName + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
