package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFilter is a Querydsl query type for Filter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFilter extends EntityPathBase<Filter> {

    private static final long serialVersionUID = -1617963401L;

    public static final QFilter filter = new QFilter("filter");

    public final StringPath filterName = createString("filterName");

    public final StringPath id = createString("id");

    public final SetPath<ParameterValue, QParameterValue> parametervalues = this.<ParameterValue, QParameterValue>createSet("parametervalues", ParameterValue.class, QParameterValue.class, PathInits.DIRECT2);

    public QFilter(String variable) {
        super(Filter.class, forVariable(variable));
    }

    public QFilter(Path<? extends Filter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilter(PathMetadata metadata) {
        super(Filter.class, metadata);
    }

}

