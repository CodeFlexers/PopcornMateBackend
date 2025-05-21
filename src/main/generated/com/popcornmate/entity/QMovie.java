package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = -1856740094L;

    public static final QMovie movie = new QMovie("movie");

    public final StringPath backdropPath = createString("backdropPath");

    public final BooleanPath isAdult = createBoolean("isAdult");

    public final BooleanPath isVideo = createBoolean("isVideo");

    public final NumberPath<Long> movieCode = createNumber("movieCode", Long.class);

    public final StringPath originalLanguage = createString("originalLanguage");

    public final StringPath overview = createString("overview");

    public final NumberPath<Float> popularity = createNumber("popularity", Float.class);

    public final StringPath posterPath = createString("posterPath");

    public final DateTimePath<java.time.LocalDateTime> releaseDate = createDateTime("releaseDate", java.time.LocalDateTime.class);

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Float> voteAverage = createNumber("voteAverage", Float.class);

    public final NumberPath<Integer> voteCount = createNumber("voteCount", Integer.class);

    public QMovie(String variable) {
        super(Movie.class, forVariable(variable));
    }

    public QMovie(Path<? extends Movie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovie(PathMetadata metadata) {
        super(Movie.class, metadata);
    }

}

