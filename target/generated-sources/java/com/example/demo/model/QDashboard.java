package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDashboard is a Querydsl query type for Dashboard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDashboard extends EntityPathBase<Dashboard> {

    private static final long serialVersionUID = 1503890229L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDashboard dashboard = new QDashboard("dashboard");

    public final StringPath dashboardName = createString("dashboardName");

    public final StringPath id = createString("id");

    public final QUser user;

    public final SetPath<Widget, QWidget> widgets = this.<Widget, QWidget>createSet("widgets", Widget.class, QWidget.class, PathInits.DIRECT2);

    public QDashboard(String variable) {
        this(Dashboard.class, forVariable(variable), INITS);
    }

    public QDashboard(Path<? extends Dashboard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDashboard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDashboard(PathMetadata metadata, PathInits inits) {
        this(Dashboard.class, metadata, inits);
    }

    public QDashboard(Class<? extends Dashboard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

