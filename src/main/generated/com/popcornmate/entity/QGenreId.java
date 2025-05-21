package com.popcornmate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenreId is a Querydsl query type for GenreId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGenreId extends BeanPath<GenreId> {

    private static final long serialVersionUID = 1055700816L;

    public static final QGenreId genreId = new QGenreId("genreId");

    public final NumberPath<Integer> genreNameCode = createNumber("genreNameCode", Integer.class);

    public final NumberPath<Long> movieCode = createNumber("movieCode", Long.class);

    public QGenreId(String variable) {
        super(GenreId.class, forVariable(variable));
    }

    public QGenreId(Path<? extends GenreId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenreId(PathMetadata metadata) {
        super(GenreId.class, metadata);
    }

}

