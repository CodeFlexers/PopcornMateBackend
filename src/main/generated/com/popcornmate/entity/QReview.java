package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -1590457402L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final ListPath<ReviewComment, QReviewComment> commentList = this.<ReviewComment, QReviewComment>createList("commentList", ReviewComment.class, QReviewComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final BooleanPath isEdit = createBoolean("isEdit");

    public final QMovie movie;

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    public final ListPath<ReviewReaction, QReviewReaction> reactionList = this.<ReviewReaction, QReviewReaction>createList("reactionList", ReviewReaction.class, QReviewReaction.class, PathInits.DIRECT2);

    public final ListPath<ReportedReview, QReportedReview> reportedList = this.<ReportedReview, QReportedReview>createList("reportedList", ReportedReview.class, QReportedReview.class, PathInits.DIRECT2);

    public final NumberPath<Integer> reviewCode = createNumber("reviewCode", Integer.class);

    public final QUser user;

    public final DateTimePath<java.time.LocalDateTime> wroteAt = createDateTime("wroteAt", java.time.LocalDateTime.class);

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new QMovie(forProperty("movie")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

