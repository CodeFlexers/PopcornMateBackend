package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserActivity is a Querydsl query type for UserActivity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserActivity extends EntityPathBase<UserActivity> {

    private static final long serialVersionUID = -493268504L;

    public static final QUserActivity userActivity = new QUserActivity("userActivity");

    public final NumberPath<Long> movieCode = createNumber("movieCode", Long.class);

    public final NumberPath<Integer> timeOnPage = createNumber("timeOnPage", Integer.class);

    public final NumberPath<Integer> userActivityCode = createNumber("userActivityCode", Integer.class);

    public final NumberPath<Integer> userCode = createNumber("userCode", Integer.class);

    public QUserActivity(String variable) {
        super(UserActivity.class, forVariable(variable));
    }

    public QUserActivity(Path<? extends UserActivity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserActivity(PathMetadata metadata) {
        super(UserActivity.class, metadata);
    }

}

