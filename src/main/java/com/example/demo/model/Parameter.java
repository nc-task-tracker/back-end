package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parameter", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @Enumerated(EnumType.STRING)
    private ParameterType parameterType;
    private String parameterValue;
    @ElementCollection
    private List<String> parameterValues;

//    @ManyToOne
//    @JoinColumn(name = "filterId", referencedColumnName = "id")
//    private Filter filter;

//    public Parameter(ParameterType parameterType, String parameterValue, List<String> parameterValues, Filter filter) {
//        this.parameterType = parameterType;
//        this.parameterValue = parameterValue;
//        this.parameterValues = parameterValues;
//        this.filter = filter;
//    }

    public Parameter(ParameterType parameterType, String parameterValue, List<String> parameterValues) {
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.parameterValues = parameterValues;
    }
}
