package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParameterValue is a Querydsl query type for ParameterValue
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParameterValue extends EntityPathBase<ParameterValue> {

    private static final long serialVersionUID = 336423751L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParameterValue parameterValue = new QParameterValue("parameterValue");

    public final StringPath id = createString("id");

    public final QParameter parameter;

    public final StringPath value = createString("value");

    public QParameterValue(String variable) {
        this(ParameterValue.class, forVariable(variable), INITS);
    }

    public QParameterValue(Path<? extends ParameterValue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParameterValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParameterValue(PathMetadata metadata, PathInits inits) {
        this(ParameterValue.class, metadata, inits);
    }

    public QParameterValue(Class<? extends ParameterValue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parameter = inits.isInitialized("parameter") ? new QParameter(forProperty("parameter")) : null;
    }

}

