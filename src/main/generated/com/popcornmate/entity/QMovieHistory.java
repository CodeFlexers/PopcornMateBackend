package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieHistory is a Querydsl query type for MovieHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieHistory extends EntityPathBase<MovieHistory> {

    private static final long serialVersionUID = 1226494258L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovieHistory movieHistory = new QMovieHistory("movieHistory");

    public final NumberPath<Integer> likeScore = createNumber("likeScore", Integer.class);

    public final QMovie movie;

    public final NumberPath<Integer> movieHistoryCode = createNumber("movieHistoryCode", Integer.class);

    public final NumberPath<Integer> userCode = createNumber("userCode", Integer.class);

    public QMovieHistory(String variable) {
        this(MovieHistory.class, forVariable(variable), INITS);
    }

    public QMovieHistory(Path<? extends MovieHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovieHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovieHistory(PathMetadata metadata, PathInits inits) {
        this(MovieHistory.class, metadata, inits);
    }

    public QMovieHistory(Class<? extends MovieHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new QMovie(forProperty("movie")) : null;
    }

}

