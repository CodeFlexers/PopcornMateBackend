package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportedReview is a Querydsl query type for ReportedReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportedReview extends EntityPathBase<ReportedReview> {

    private static final long serialVersionUID = -989862791L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportedReview reportedReview = new QReportedReview("reportedReview");

    public final StringPath reason = createString("reason");

    public final NumberPath<Integer> reportedReviewCode = createNumber("reportedReviewCode", Integer.class);

    public final QReview review;

    public final QUser user;

    public QReportedReview(String variable) {
        this(ReportedReview.class, forVariable(variable), INITS);
    }

    public QReportedReview(Path<? extends ReportedReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportedReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportedReview(PathMetadata metadata, PathInits inits) {
        this(ReportedReview.class, metadata, inits);
    }

    public QReportedReview(Class<? extends ReportedReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

