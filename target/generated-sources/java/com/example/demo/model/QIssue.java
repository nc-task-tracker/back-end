package com.example.demo.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIssue is a Querydsl query type for Issue
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIssue extends EntityPathBase<Issue> {

    private static final long serialVersionUID = -1711685126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIssue issue = new QIssue("issue");

    public final QProfile assignee;

    public final SetPath<Comment, QComment> comments = this.<Comment, QComment>createSet("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final DatePath<java.sql.Date> dueDate = createDate("dueDate", java.sql.Date.class);

    public final StringPath id = createString("id");

    public final StringPath issueCode = createString("issueCode");

    public final StringPath issueDescription = createString("issueDescription");

    public final StringPath issueName = createString("issueName");

    public final EnumPath<IssuePriority> issuePriority = createEnum("issuePriority", IssuePriority.class);

    public final EnumPath<IssueStatus> issueStatus = createEnum("issueStatus", IssueStatus.class);

    public final EnumPath<IssueType> issueType = createEnum("issueType", IssueType.class);

    public final StringPath parentId = createString("parentId");

    public final QProject project;

    public final QProfile reporter;

    public final DatePath<java.sql.Date> startDate = createDate("startDate", java.sql.Date.class);

    public final SetPath<Issue, QIssue> subtasks = this.<Issue, QIssue>createSet("subtasks", Issue.class, QIssue.class, PathInits.DIRECT2);

    public QIssue(String variable) {
        this(Issue.class, forVariable(variable), INITS);
    }

    public QIssue(Path<? extends Issue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIssue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIssue(PathMetadata metadata, PathInits inits) {
        this(Issue.class, metadata, inits);
    }

    public QIssue(Class<? extends Issue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assignee = inits.isInitialized("assignee") ? new QProfile(forProperty("assignee"), inits.get("assignee")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.reporter = inits.isInitialized("reporter") ? new QProfile(forProperty("reporter"), inits.get("reporter")) : null;
    }

}

