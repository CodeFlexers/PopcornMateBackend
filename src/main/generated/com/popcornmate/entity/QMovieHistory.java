package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMovieHistory is a Querydsl query type for MovieHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieHistory extends EntityPathBase<MovieHistory> {

    private static final long serialVersionUID = 1226494258L;

    public static final QMovieHistory movieHistory = new QMovieHistory("movieHistory");

    public final NumberPath<Long> movieCode = createNumber("movieCode", Long.class);

    public final NumberPath<Integer> movieHistoryCode = createNumber("movieHistoryCode", Integer.class);

    public final NumberPath<Integer> userCode = createNumber("userCode", Integer.class);

    public QMovieHistory(String variable) {
        super(MovieHistory.class, forVariable(variable));
    }

    public QMovieHistory(Path<? extends MovieHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovieHistory(PathMetadata metadata) {
        super(MovieHistory.class, metadata);
    }

}

