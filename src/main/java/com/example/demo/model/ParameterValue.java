package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "parametervalues", schema = "new_schema")
@Getter
@Setter
@NoArgsConstructor
public class ParameterValue {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String value;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "parameterId", referencedColumnName = "id")
    private Parameter parameter;

    public ParameterValue(String value, Parameter parameter) {
        this.value = value;
        this.parameter = parameter;
    }
}
