package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdentificator is a Querydsl query type for Identificator
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIdentificator extends EntityPathBase<Identificator> {

    private static final long serialVersionUID = -981997858L;

    public static final QIdentificator identificator = new QIdentificator("identificator");

    public final NumberPath<Long> curFreedom = createNumber("curFreedom", Long.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QIdentificator(String variable) {
        super(Identificator.class, forVariable(variable));
    }

    public QIdentificator(Path<? extends Identificator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdentificator(PathMetadata metadata) {
        super(Identificator.class, metadata);
    }

}

