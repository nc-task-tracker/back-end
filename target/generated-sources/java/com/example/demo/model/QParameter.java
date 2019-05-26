package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QParameter is a Querydsl query type for Parameter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParameter extends EntityPathBase<Parameter> {

    private static final long serialVersionUID = 211244106L;

    public static final QParameter parameter = new QParameter("parameter");

    public final StringPath id = createString("id");

    public final StringPath parameterName = createString("parameterName");

    public QParameter(String variable) {
        super(Parameter.class, forVariable(variable));
    }

    public QParameter(Path<? extends Parameter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParameter(PathMetadata metadata) {
        super(Parameter.class, metadata);
    }

}

