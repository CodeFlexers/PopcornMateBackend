package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserActivity is a Querydsl query type for UserActivity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserActivity extends EntityPathBase<UserActivity> {

    private static final long serialVersionUID = -493268504L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserActivity userActivity = new QUserActivity("userActivity");

    public final QMovie movie;

    public final NumberPath<Integer> timeOnPage = createNumber("timeOnPage", Integer.class);

    public final NumberPath<Integer> userActivityCode = createNumber("userActivityCode", Integer.class);

    public final NumberPath<Integer> userCode = createNumber("userCode", Integer.class);

    public QUserActivity(String variable) {
        this(UserActivity.class, forVariable(variable), INITS);
    }

    public QUserActivity(Path<? extends UserActivity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserActivity(PathMetadata metadata, PathInits inits) {
        this(UserActivity.class, metadata, inits);
    }

    public QUserActivity(Class<? extends UserActivity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new QMovie(forProperty("movie")) : null;
    }

}

