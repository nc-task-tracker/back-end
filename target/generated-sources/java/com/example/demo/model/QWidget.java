package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWidget is a Querydsl query type for Widget
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWidget extends EntityPathBase<Widget> {

    private static final long serialVersionUID = -1131518653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWidget widget = new QWidget("widget");

    public final StringPath Assign = createString("Assign");

    public final StringPath description = createString("description");

    public final QFilter filter;

    public final StringPath id = createString("id");

    public final StringPath Priority = createString("Priority");

    public final StringPath type = createString("type");

    public final StringPath widgetName = createString("widgetName");

    public final StringPath widgetOrder = createString("widgetOrder");

    public QWidget(String variable) {
        this(Widget.class, forVariable(variable), INITS);
    }

    public QWidget(Path<? extends Widget> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWidget(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWidget(PathMetadata metadata, PathInits inits) {
        this(Widget.class, metadata, inits);
    }

    public QWidget(Class<? extends Widget> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.filter = inits.isInitialized("filter") ? new QFilter(forProperty("filter")) : null;
    }

}

