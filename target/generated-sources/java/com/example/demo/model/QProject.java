package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1927979002L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final SetPath<Dashboard, QDashboard> dashboards = this.<Dashboard, QDashboard>createSet("dashboards", Dashboard.class, QDashboard.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final QUser owner;

    public final StringPath projectCode = createString("projectCode");

    public final StringPath projectDescription = createString("projectDescription");

    public final StringPath projectName = createString("projectName");

    public final EnumPath<ProjectStatus> projectStatus = createEnum("projectStatus", ProjectStatus.class);

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProject(PathMetadata metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QUser(forProperty("owner"), inits.get("owner")) : null;
    }

}

